package com.youjuke.optimalmaterialtreasure.app.site;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.youjuke.library.base.BaseFragment;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.optimalmaterialtreasure.OptimalMaterialTreasureApp;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.login.LoginActivity;
import com.youjuke.optimalmaterialtreasure.entity.SiteInfo;
import com.youjuke.optimalmaterialtreasure.retrofit.ApiContstants;
import com.youjuke.optimalmaterialtreasure.retrofit.RequestBean;
import com.youjuke.optimalmaterialtreasure.retrofit.ResponseBean;
import com.youjuke.optimalmaterialtreasure.retrofit.RetrofitManager;
import com.youjuke.optimalmaterialtreasure.retrofit.api.CommonService;
import com.youjuke.optimalmaterialtreasure.weights.SweetAlertDialog;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.ListenerStubs;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述:工地地址界面
 * author：zyb
 * Created by Administrator on 2017/2/8.
 */

public class SiteFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recycle;
    private TextView tv_add_site;
    private SiteAdapter siteAdapter;
    private ArrayList<SiteInfo> datas = new ArrayList();
    private float off = 0f;
    private static final int MIXOFF = 100;//偏移量小于这个值不刷新
    private ImageView iv_no_site;
    private TextView tv_hint;
    private View vv;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_site;
    }

    @Override
    public void finishCreateView(Bundle state) {
        initView();
    }

    SweetAlertDialog.Builder builder;

    private void initView() {
        recycle = $(R.id.rlv_recycle);
        tv_add_site = $(R.id.tv_add_site);
        tv_add_site.setOnClickListener(this);
        iv_no_site = $(R.id.iv_no_site);
        tv_hint = $(R.id.tv_hint);
        vv = $(R.id.vv);
        recycle.setLayoutManager(new LinearLayoutManager(mContext));
        siteAdapter = new SiteAdapter(mContext, datas);
        recycle.setAdapter(siteAdapter);
        siteAdapter.setOnClickListener(new SiteAdapter.OnClickListener() {
            @Override
            public void deleteClickListener(final int gd_id, final int position) {
                if (builder == null) {
                    builder = new SweetAlertDialog.Builder(mContext);
                }
                builder.setCancelable(true)
                        .setMessage("您确定要删除吗？")
                        .setTitle("提示")
                        .setCancelableoutSide(false)
                        .setPositiveButton("确定", new SweetAlertDialog.OnDialogClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                deleteSite(gd_id, position);
                            }
                        }).show();

            }

            @Override
            public void editClickListener(int gd_id) {
                Intent intent = new Intent(mContext, AddSiteActivity.class);
                intent.putExtra("gd_id", gd_id);
                startActivity(intent);
            }
        });
        initdata();
        initScroll();
    }

    /**
     * @param gd_id 删除工地
     */
    private void deleteSite(int gd_id, final int position) {
        params.clear();
        myDialog.show();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        params.put("gd_id", gd_id);
        compositeSubscription.add(RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg(ApiContstants.DEL_ADDRESS, params).toJson())
                .compose(this.<ResponseBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean>() {
                               @Override
                               public void onCompleted() {
                                   myDialog.dismiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   myDialog.dismiss();
                                   ToastUtil.show(getApplicationContext(), "删除失败");
                               }
                               @Override
                               public void onNext(ResponseBean responseBean) {
                                   //成功
                                   if (responseBean.getStatus().equals("200")) {
                                       ToastUtil.show(getApplicationContext(), responseBean.getMessage());
                                       datas.remove(position);
                                       siteAdapter.notifyDataSetChanged();
                                       if (datas.size()==0){
                                           iv_no_site.setVisibility(View.VISIBLE);
                                           tv_hint.setVisibility(View.VISIBLE);
                                           vv.setVisibility(View.GONE);
                                           recycle.setVisibility(View.GONE);
                                       }
                                   } else if (responseBean.getStatus().equals("400")) {
                                       ToastUtil.show(getApplicationContext(), responseBean.getMessage());
                                   }
                               }
                           }
                )
        );
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.isVisible())
            initdata();
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initdata();
        }
    }
    private void initdata() {
        params.clear();
        myDialog.show();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        compositeSubscription.add(RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg(ApiContstants.ADDRESS_LIST, params).toJson())
                .compose(this.<ResponseBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBean>() {
                               @Override
                               public void onCompleted() {
                                   myDialog.dismiss();
                               }

                               @Override
                               public void onError(Throwable e) {
                                   myDialog.dismiss();
                                   ToastUtil.show(getApplicationContext(), "请求数据失败,请下滑刷新");
                               }

                               @Override
                               public void onNext(ResponseBean responseBean) {
                                   //成功
                                   if (responseBean.getStatus().equals("200")) {
                                       if (gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<SiteInfo>>() {
                                       }.getType()) != null) {
                                           datas = gson.fromJson(responseBean.getData(), new TypeToken<ArrayList<SiteInfo>>() {
                                           }.getType());
                                           if (datas.size() == 0) {
                                               iv_no_site.setVisibility(View.VISIBLE);
                                               tv_hint.setVisibility(View.VISIBLE);
                                               vv.setVisibility(View.GONE);
                                               recycle.setVisibility(View.GONE);
                                           } else {
                                               iv_no_site.setVisibility(View.GONE);
                                               tv_hint.setVisibility(View.GONE);
                                               vv.setVisibility(View.VISIBLE);
                                               recycle.setVisibility(View.VISIBLE);
                                               siteAdapter.setdata(datas);
                                               siteAdapter.notifyDataSetChanged();
                                           }
                                       } else {
                                           ToastUtil.show(getApplicationContext(), "请添加工地");
                                       }
                                   } else if (responseBean.getStatus().equals("400") && responseBean.getError().equals("020")) {
                                       ToastUtil.show(getApplicationContext(), responseBean.getMessage());
                                       startActivity(new Intent(mContext, LoginActivity.class));
                                   }
                               }
                           }
                )
        );
    }

    private void initScroll() {
        IOverScrollDecor iOverScrollDecor = OverScrollDecoratorHelper.setUpOverScroll(recycle, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        View view = iOverScrollDecor.getView();
        view.setBackgroundResource(R.color.back_f0f0f0);
        iOverScrollDecor.setOverScrollStateListener(new ListenerStubs.OverScrollStateListenerStub() {
            @Override
            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
                super.onOverScrollStateChange(decor, oldState, newState);
                if (oldState == 1) {
                    if (off > MIXOFF) {
                        initdata();
                    }
                }
            }
        });
        //滑动状态监听，state 0-1-3 往下滑动 0-2-3往上滑动 offset滑动的偏移量
        iOverScrollDecor.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
                off = offset;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_site:
                Intent intent = new Intent(mContext, AddSiteActivity.class);
                intent.putExtra("gd_id", -1);
                startActivity(intent);
                break;
        }
    }
}
