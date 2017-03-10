package com.youjuke.optimalmaterialtreasure.wxapi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.library.rxbus.RxBus;
import com.youjuke.library.rxbus.annotation.Subscribe;
import com.youjuke.library.rxbus.annotation.Tag;
import com.youjuke.library.rxbus.thread.EventThread;
import com.youjuke.library.utils.L;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.MainActivity;
import com.youjuke.optimalmaterialtreasure.app.rechargeable.RechargeableActivity;


/**
 * 微信支付回调接收页面
 */
public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler,View.OnClickListener {

    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;
    private android.support.v7.app.AlertDialog.Builder builder;
    private android.support.v7.app.AlertDialog dialog;

    private Toolbar payToolBar;
    private TextView toolBarTextView;
    private ImageView imageView;
    private TextView tvPayTitle;
    private TextView tvPayMessage;
    private Button buttonConfirm;
    private TextView textToShoppingOrder;
    private int errCode = -1;
    private LinearLayout llScene;
    private String orderId="";

    private void assignViews() {
        payToolBar = (Toolbar) findViewById(R.id.pay_tool_bar);
        toolBarTextView = (TextView) findViewById(R.id.tool_bar_text_view);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvPayTitle = (TextView) findViewById(R.id.tv_pay_title);
        tvPayMessage = (TextView) findViewById(R.id.tv_pay_message);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        textToShoppingOrder = (TextView) findViewById(R.id.text_to_shopping_order);
        llScene= (LinearLayout) findViewById(R.id.ll_scene);
        buttonConfirm.setOnClickListener(this);
        textToShoppingOrder.setOnClickListener(this);
    }


    @Override
    public void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"),0);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
        assignViews();
        llScene.setVisibility(View.INVISIBLE);
        toolBarTextView.setText("充值");

        tvPayMessage.setVisibility(View.INVISIBLE);
        buttonConfirm.setText("返回");
        textToShoppingOrder.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pay_result;
    }

    @Override
    public void initToolBar() {
        payToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            out();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void out() {
        if (errCode==0) {
            ActivityManager.getInstance().finishActivity(RechargeableActivity.class);
        }
        this.finish();
    }



    @Override
    public void onResp(BaseResp resp) {
        L.d("onPayFinish, errCode = " + resp.errCode);
        errCode = resp.errCode;
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
               handler.sendEmptyMessage(resp.errCode);
        }
    }

 private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {//支付成功
                imageView.setImageResource(R.mipmap.btn_czcg);
                tvPayTitle.setText("充值成功");
            }else {
                imageView.setImageResource(R.mipmap.btn_zfsb);
                tvPayTitle.setText("充值失败");
            }
                llScene.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_confirm://去主页
                out();
                break;
            default:
                break;

        }
    }
}