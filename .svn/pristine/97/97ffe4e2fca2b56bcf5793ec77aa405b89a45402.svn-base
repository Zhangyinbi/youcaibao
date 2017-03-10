package com.youjuke.library.weights;

import android.content.Context;
import android.content.DialogInterface;

import com.youjuke.library.R;


/**
 * Created by Administrator on 2016/11/29.
 */

public class MyDialog {
    CustomProgressDialog mCustomProgressDialog;

    public MyDialog(Context context) {
        this.context = context;
    }

    Context context;
    /**
     * 显示一个ProgressDialog
     */
    public void show() {

        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(context, R.drawable.anim_progressr);
        }
        mCustomProgressDialog.setCancelable(true);// 设置按返回键是否关闭dialog
        mCustomProgressDialog.setCanceledOnTouchOutside(false);
        if (!mCustomProgressDialog.isShowing()){
            mCustomProgressDialog.show();
        }
    }

    /**
     * 取消当前显示的ProgressDialog
     */
    public void dismiss(){
            if (mCustomProgressDialog != null
                    && mCustomProgressDialog.isShowing()) {
                mCustomProgressDialog.dismiss();
            }

    }
    public void dimissListener(DialogInterface.OnDismissListener onDismissListener){
        mCustomProgressDialog.setOnDismissListener(onDismissListener);
    }
}
