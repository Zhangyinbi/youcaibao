package com.youjuke.optimalmaterialtreasure.app.rechargeable;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.MainActivity;
import com.youjuke.optimalmaterialtreasure.app.catalogue.GoodsDetailActivity;
import com.youjuke.optimalmaterialtreasure.app.catalogue.GoodsListActivity;
import com.youjuke.optimalmaterialtreasure.app.shopping_cart.OrderConfirmationFragment;
import com.youjuke.optimalmaterialtreasure.app.shopping_cart.ShoppingCartActivity;

/**
 * 描述: 支付成功
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-20 18:09
 */
public class PaySuccessActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar payToolBar;
    private TextView toolBarTextView;
    private LinearLayout llScene;
    private ImageView imageView;
    private TextView tvPayTitle;
    private TextView tvPayMessage;
    private Button buttonConfirm;
    private TextView textToShoppingOrder;
    private String payResult;

    private void assignViews() {
        payToolBar = (Toolbar) findViewById(R.id.pay_tool_bar);
        toolBarTextView = (TextView) findViewById(R.id.tool_bar_text_view);
        llScene = (LinearLayout) findViewById(R.id.ll_scene);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvPayTitle = (TextView) findViewById(R.id.tv_pay_title);
        tvPayMessage = (TextView) findViewById(R.id.tv_pay_message);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        textToShoppingOrder = (TextView) findViewById(R.id.text_to_shopping_order);
        buttonConfirm.setOnClickListener(this);
        textToShoppingOrder.setOnClickListener(this);
    }


    @Override
    public void initViews(Bundle savedInstanceState) {
        payResult = getIntent().getStringExtra("payResult");
        assignViews();
        if (payResult != null && !payResult.isEmpty()) {

            toolBarTextView.setText("充值");

            tvPayMessage.setVisibility(View.INVISIBLE);
            buttonConfirm.setText("返回");
            textToShoppingOrder.setVisibility(View.INVISIBLE);
            if (TextUtils.equals(payResult, "9000")) {
                //支付宝充值成功
                imageView.setImageResource(R.mipmap.btn_czcg);
                tvPayTitle.setText("充值成功");
            } else {
                imageView.setImageResource(R.mipmap.btn_zfsb);
                tvPayTitle.setText("充值失败");
            }
        }

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
                ActivityManager.getInstance().finishActivity(PaySuccessActivity.this);
                ActivityManager.getInstance().finishActivity(ShoppingCartActivity.class);
            }
        });
    }

    public void out() {
        if (payResult != null && !payResult.isEmpty()) {
            if (TextUtils.equals(payResult, "9000")) {
                //支付宝充值成功
                ActivityManager.getInstance().finishActivity(RechargeableActivity.class);
            }

        } else {
            if (type == 1) {//去首页
                MainActivity.first = 1;
            } else if (type == 2) {//去订单列表页
                MainActivity.second = 2;
            }
            ActivityManager.getInstance().finishActivity(ShoppingCartActivity.class);
            ActivityManager.getInstance().finishActivity(GoodsListActivity.class);
            ActivityManager.getInstance().finishActivity(GoodsDetailActivity.class);
        }


        ActivityManager.getInstance().finishActivity(this);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            out();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    int type = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_confirm:
                type = 1;
                out();
                break;
            case R.id.text_to_shopping_order:

                type = 2;
                out();
                break;
        }
    }
}
