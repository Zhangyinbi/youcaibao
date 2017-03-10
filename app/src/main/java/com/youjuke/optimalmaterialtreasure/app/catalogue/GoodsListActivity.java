package com.youjuke.optimalmaterialtreasure.app.catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.google.gson.reflect.TypeToken;
import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.library.utils.L;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.MaterialGoodsDetails;
import com.youjuke.optimalmaterialtreasure.entity.MaterialsClassify;
import com.youjuke.optimalmaterialtreasure.retrofit.ApiContstants;
import com.youjuke.optimalmaterialtreasure.retrofit.RequestBean;
import com.youjuke.optimalmaterialtreasure.retrofit.ResponseBean;
import com.youjuke.optimalmaterialtreasure.retrofit.RetrofitManager;
import com.youjuke.optimalmaterialtreasure.retrofit.api.CommonService;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：商品列表
 * author：zyb
 * Created by Administrator on 2017/2/13.
 */

public class GoodsListActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private ImageView ivBack;
    private int fid;
    private int cid;
    private int gid;
    private RadioGroup rgGroup;
    private int[] id = {R.id.rb_water, R.id.rb_electricity, R.id.rb_mud, R.id.rb_wood, R.id.rb_oil, R.id.rb_metals};
    private RecyclerView brandCycler;
    private RecyclerView goodsCycler;
    private ArrayList<MaterialsClassify> brands = new ArrayList();
    private ArrayList<MaterialGoodsDetails> materialgoods = new ArrayList();
    private BrandsAdapter brandsAdapter;
    private MaterialAdapter materialAdapter;

    @Override
    public void initViews(Bundle savedInstanceState) {
        fid = getIntent().getIntExtra("fid", -1);
        cid=getIntent().getIntExtra("cid", -1);
        gid=getIntent().getIntExtra("gid", -1);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);


        brandCycler = (RecyclerView) findViewById(R.id.rlv_recycle_brand);
        brandsAdapter = new BrandsAdapter(this, brands,cid);
        brandsAdapter.setOnItemClickListener(new BrandsAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int cid) {
                getgoodsdetails(cid);
            }
        });
        brandCycler.setLayoutManager(new LinearLayoutManager(this));
        brandCycler.setAdapter(brandsAdapter);


        goodsCycler = (RecyclerView) findViewById(R.id.rlv_recycle_goodsdetail);
        materialAdapter=new MaterialAdapter(this,materialgoods);
        materialAdapter.setOnItemClickListener(new MaterialAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int material_config_id) {
                Intent intent = new Intent(GoodsListActivity.this, GoodsDetailActivity.class);
                intent.putExtra("material_config_id",material_config_id);
                startActivity(intent);
            }
        });
        goodsCycler.setLayoutManager(new LinearLayoutManager(this));
        goodsCycler.setAdapter(materialAdapter);
        rgGroup.check(id[fid - 5]);
        rgGroup.setOnCheckedChangeListener(this);
        initData();
    }

    /**
     * s商品列表信息   三级列表
     */
    private void getgoodsdetails(int cid) {
        myDialog.show();
        materialgoods.clear();
        params.clear();
        params.put("cid", cid);
        Subscription subscribe = RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg(ApiContstants.MATERIALS_CONFIG_LIST, params).toJson())
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
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        L.i("三级列表数据Data:" + responseBean.getData());
                        if ("200".equals(responseBean.getStatus())) {
                            if (gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<MaterialsClassify>>() {
                            }.getType())!=null) {
                                materialgoods = gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<MaterialGoodsDetails>>() {
                                }.getType());
                                materialAdapter.setdata(materialgoods);
                            }
                            materialAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.show(GoodsListActivity.this, responseBean.getMessage());
                        }
                    }
                });
        compositeSubscription.add(subscribe);
    }

    private void initData() {
        brands.clear();
        materialgoods.clear();
        myDialog.show();
        params.clear();
        params.put("fid", fid);
        Subscription subscribe = RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg(ApiContstants.MATERIALS_CLASSIFY, params).toJson())
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
                    }

                    @Override
                    public void onNext(ResponseBean responseBean) {
                        L.i("首页数据Data:" + responseBean.getData());
                        if ("200".equals(responseBean.getStatus())) {
                            if (gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<MaterialsClassify>>() {
                            }.getType())!=null) {
                                brands = gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<MaterialsClassify>>() {
                                }.getType());
                                brandsAdapter.setdata(brands);
                            }
                            brandsAdapter.notifyDataSetChanged();
                            materialAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.show(GoodsListActivity.this, responseBean.getMessage());
                        }
                    }
                });
        compositeSubscription.add(subscribe);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goodslist;
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
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_water:
                fid = 5;
                break;
            case R.id.rb_electricity:
                fid = 6;
                break;
            case R.id.rb_mud:
                fid = 7;
                break;
            case R.id.rb_wood:
                fid = 8;
                break;
            case R.id.rb_oil:
                fid = 9;
                break;
            case R.id.rb_metals:
                fid = 10;
                break;
        }
        brandsAdapter.setCid(-1);
        brandsAdapter.setCurrent(0);
        initData();
    }
}
