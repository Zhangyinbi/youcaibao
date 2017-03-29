package com.youjuke.optimalmaterialtreasure.app.shopping_cart;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.optimalmaterialtreasure.R;

/**
 * Created by Administrator on 2017/3/20.
 */

public class DialogTime {
    Context context;
    private Dialog mDialog;
    public DialogTime(Context context) {
        this.context = context;
        initView();
    }
    private void initView() {
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_time, null);
        // 定义Dialog布局和参数
        mDialog = new Dialog(context, R.style.Sweet_Alert_Dialog);
        mDialog.setContentView(rootView);
        mDialog.setCanceledOnTouchOutside(true);
        /*Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);*/

        WindowManager windowManager = ((BaseActivity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()*0.9); //设置宽度
        mDialog.getWindow().setAttributes(lp);
    }
    public void show() {
        if (mDialog != null)
            mDialog.show();
    }
}
