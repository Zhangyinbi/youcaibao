package com.youjuke.optimalmaterialtreasure.app.catalogue;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.library.utils.L;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.optimalmaterialtreasure.OptimalMaterialTreasureApp;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.login.LoginActivity;
import com.youjuke.optimalmaterialtreasure.app.shopping_cart.ShoppingCartActivity;
import com.youjuke.optimalmaterialtreasure.entity.MaterialDetails;
import com.youjuke.optimalmaterialtreasure.retrofit.ApiContstants;
import com.youjuke.optimalmaterialtreasure.retrofit.RequestBean;
import com.youjuke.optimalmaterialtreasure.retrofit.ResponseBean;
import com.youjuke.optimalmaterialtreasure.retrofit.RetrofitManager;
import com.youjuke.optimalmaterialtreasure.retrofit.api.CommonService;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：选择商品规格
 * author：zyb
 * Created by Administrator on 2017/2/14.
 */

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener, GoodsDetailAdapter.OnItemClickListener {

    private ImageView ivBack;
    private int material_config_id;
    private RecyclerView rlvRecycler;
    private ArrayList<MaterialDetails> datas = new ArrayList();
    private GoodsDetailAdapter adapter;
    private AddCartDialog cartDialog;
    private ImageView iv_icon;//购物车有没有数据
    private TextView tv_count;//购物车数量
    private AutoRelativeLayout arl_no_data;//没有数据
    private AutoLinearLayout arl_you_data;//有数据
    private TextView tv_settlement;//结算

    @Override
    protected void onResume() {
        super.onResume();
        getDataGoodCartInfo();
        initdata();
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        material_config_id = getIntent().getIntExtra("material_config_id", -1);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_icon.setOnClickListener(this);
        tv_count = (TextView) findViewById(R.id.tv_count);
        arl_no_data = (AutoRelativeLayout) findViewById(R.id.arl_no_data);
        arl_you_data = (AutoLinearLayout) findViewById(R.id.arl_you_data);
        tv_settlement = (TextView) findViewById(R.id.tv_settlement);
        tv_settlement.setOnClickListener(this);
        rlvRecycler = (RecyclerView) findViewById(R.id.rlv_recycler);
        rlvRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoodsDetailAdapter(this, datas);
        rlvRecycler.setAdapter(adapter);
        OverScrollDecoratorHelper.setUpOverScroll(rlvRecycler, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        adapter.setOnItemClickListener(this);

    }

    /**
     * 获取购物车信息
     */
    private void getDataGoodCartInfo() {
        params.clear();
        myDialog.show();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        Subscription subscribe = RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg(ApiContstants.SMALL_CART, params).toJson())
                .compose(this.<ResponseBean>bindToLifecycle())  //生命周期绑定
                .observeOn(AndroidSchedulers.mainThread())      //设置观察者所在的线程
                .subscribeOn(Schedulers.io())                   //设置被观察者所在的线程
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.i("数据加载失败！");
                        e.printStackTrace();
                        myDialog.dismiss();
                        ToastUtil.show(GoodsDetailActivity.this, "数据加载失败");
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        L.i("数据Data:" + responseBean.getData());
                        if ("200".equals(responseBean.getStatus())) {
                            from = 0;
                            try {
                                JSONObject jsonObject = new JSONObject(responseBean.getData());
                                String count = jsonObject.getString("count");
                                if ("0".equals(count) || count.length() == 0 || "null".equals(count)) {
                                    arl_no_data.setVisibility(View.VISIBLE);
                                    arl_you_data.setVisibility(View.GONE);
                                    iv_icon.setImageResource(R.mipmap.btn_gwc_3);
                                    iv_icon.setEnabled(false);
                                    tv_count.setVisibility(View.GONE);
                                } else {
                                    arl_no_data.setVisibility(View.GONE);
                                    arl_you_data.setVisibility(View.VISIBLE);
                                    iv_icon.setImageResource(R.mipmap.btn_gwc_4);
                                    iv_icon.setEnabled(true);
                                    tv_count.setVisibility(View.VISIBLE);
                                    tv_count.setText(count);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtil.show(GoodsDetailActivity.this, responseBean.getMessage());
                        }
                    }
                });
        compositeSubscription.add(subscribe);
    }

    private void initdata() {
        params.clear();
        myDialog.show();
        params.put("material_config_id", material_config_id);
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        Subscription subscribe = RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg(ApiContstants.MATERIAL_DETAIL, params).toJson())
                .compose(this.<ResponseBean>bindToLifecycle())  //生命周期绑定
                .observeOn(AndroidSchedulers.mainThread())      //设置观察者所在的线程
                .subscribeOn(Schedulers.io())                   //设置被观察者所在的线程
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.i("数据加载失败！");
                        e.printStackTrace();
                        myDialog.dismiss();
                        ToastUtil.show(GoodsDetailActivity.this, "数据加载失败");
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        L.i("数据Data:" + responseBean.getData());
                        if ("200".equals(responseBean.getStatus())) {
                            if (gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<MaterialDetails>>() {
                            }.getType()) != null) {
                                datas = gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<MaterialDetails>>() {
                                }.getType());
                                adapter.setdata(datas);
                                adapter.notifyDataSetChanged();
                            } else {
                                ToastUtil.show(GoodsDetailActivity.this, "暂无数据信息");
                            }
                        } else {
                            ToastUtil.show(GoodsDetailActivity.this, responseBean.getMessage());
                        }
                    }
                });
        compositeSubscription.add(subscribe);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                ActivityManager.getInstance().finishActivity();
                break;
            case R.id.tv_settlement:
            case R.id.iv_icon:
                startActivity(new Intent(this, ShoppingCartActivity.class));
                break;
        }
    }

    int from = 0;

    @Override
    public void ItemClickListener(MaterialDetails materialDetails) {
//        if (cartDialog!=null)cartDialog=null;
        if (cartDialog == null) {
            cartDialog = new AddCartDialog(this) {
                @Override
                public void addCart(int id, String num) {
                    addcart(id, num);
                }
            };
        }
        cartDialog.setData(materialDetails);
        cartDialog.show();
        myDialog.dimissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (from == 1) getDataGoodCartInfo();
            }
        });
    }

    private void addcart(int id, String num) {
        myDialog.show();
        params.clear();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        params.put("mobile", OptimalMaterialTreasureApp.user.getMobile());
        params.put("matId", id);
        params.put("count", num);
        compositeSubscription.add(
                RetrofitManager.getInstance().create(CommonService.class)
                        .getData(new RequestBean.JsonMsg(ApiContstants.ADD_SHOPCART, params).toJson())
                        .compose(this.<ResponseBean>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<ResponseBean>() {
                                       @Override
                                       public void onCompleted() {
                                           myDialog.dismiss();
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           L.i("数据加载失败！");
                                           e.printStackTrace();
                                           myDialog.dismiss();
                                       }

                                       @Override
                                       public void onNext(ResponseBean responseBean) {
                                           L.i("Data:" + responseBean.getData());
                                           cartDialog.dismiss();
                                           if ("200".equals(responseBean.getStatus())) {
                                               ToastUtil.show(GoodsDetailActivity.this, responseBean.getMessage());
                                               from = 1;
                                           } else if ("400".equals(responseBean.getStatus())) {
                                               ToastUtil.show(GoodsDetailActivity.this, "请登录");
                                               startActivity(new Intent(GoodsDetailActivity.this, LoginActivity.class));
                                           }
                                       }
                                   }
                        )
        );
    }
}
