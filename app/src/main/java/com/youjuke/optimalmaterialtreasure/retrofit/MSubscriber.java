package com.youjuke.optimalmaterialtreasure.retrofit;


import android.content.Context;
import android.content.Intent;

import com.youjuke.library.utils.L;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.library.weights.MyDialog;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.login.LoginActivity;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/23.
 */

public abstract class MSubscriber extends Subscriber<ResponseBean> {
    private Context mContext;
    private MyDialog myDialog;
    public MSubscriber(Context context,MyDialog myDialog) {
        mContext=context;
        this.myDialog=myDialog;
    }
    @Override
    public void onNext(ResponseBean responseBean) {
        if (myDialog!=null)myDialog.dismiss();
        if (responseBean.getStatus().equals("200")){
            mOnNextCorrect(responseBean);
        }else if (responseBean.getStatus().equals("400")){
            if (responseBean.getError().equals("020")||responseBean.getError().equals("007")||responseBean.getError().equals("010")||responseBean.getError().equals("1010")){
                ToastUtil.show(mContext,mContext.getString(R.string.error_));
                mContext.startActivity(new Intent(mContext, LoginActivity.class));
            }else {
                mOnNextFault(responseBean);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        L.e(mContext.getString(R.string.netWork_error_has_occurred));
        if (myDialog!=null)myDialog.dismiss();
        e.printStackTrace();
        ToastUtil.show(mContext,mContext.getString(R.string.netWork_error_hint));
    }

    @Override
    public void onCompleted() {
        L.e(mContext.getString(R.string.Network_request_has_ended));
        if (myDialog!=null)myDialog.dismiss();
    }

    public abstract void mOnNextCorrect(ResponseBean responseBean);
    public abstract void mOnNextFault(ResponseBean responseBean);
}
