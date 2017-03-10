package com.youjuke.library.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.youjuke.library.rxbus.RxBus;
import com.youjuke.library.weights.MyDialog;

import java.util.HashMap;
import java.util.Map;

import rx.subscriptions.CompositeSubscription;

/**
 * 描述:Fragment基类
 * <p>
 * 工程:
 * #0000    Tian Xiao    2016-09-06 13:41
 */
public abstract class BaseFragment extends RxFragment {
    /**
     * 访问网络提交数据的集合
     */
    protected Map<String, Object> params = new HashMap<String, Object>();

    protected Gson gson = new Gson();

    protected View parentView;

    protected FragmentActivity activity;

    protected LayoutInflater inflater;

    protected CompositeSubscription compositeSubscription;

    protected Context mContext;
    public MyDialog myDialog;
    public abstract
    @LayoutRes
    int getLayoutResId();

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //过场动画
        RxBus.get().register(this);
        // “内存重启”时调用  解决重叠问题  #0001
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mContext = container.getContext();
        myDialog=new MyDialog(mContext);
        //初始化Dialog
        this.inflater = inflater;
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        return parentView;

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        compositeSubscription = new CompositeSubscription();

        finishCreateView(savedInstanceState);
    }

    public abstract void finishCreateView(Bundle state);


    @Override
    public void onDestroyView() {

        super.onDestroyView();
        compositeSubscription.unsubscribe();
    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {

        super.onDetach();
        this.activity = null;
    }

    public FragmentActivity getSupportActivity() {

        return (FragmentActivity) super.getActivity();
    }

    public android.app.ActionBar getSupportActionBar() {

        return getSupportActivity().getActionBar();
    }

    public Context getApplicationContext() {

        return this.activity == null ? (getActivity() == null ? null : getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }

    protected LayoutInflater getLayoutInflater() {

        return inflater;
    }

    protected View getParentView() {

        return parentView;
    }

    public <T extends View> T $(int id) {

        return (T) parentView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
            myDialog = null;
        }
        RxBus.get().unregister(this);
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
