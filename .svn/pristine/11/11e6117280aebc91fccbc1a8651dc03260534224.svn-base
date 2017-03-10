package com.youjuke.optimalmaterialtreasure.app.rechargeable;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.rxbus.RxBus;
import com.youjuke.library.rxbus.annotation.Subscribe;
import com.youjuke.library.rxbus.annotation.Tag;
import com.youjuke.library.rxbus.thread.EventThread;
import com.youjuke.library.utils.L;
import com.youjuke.library.utils.MoneySimpleFormat;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.optimalmaterialtreasure.OptimalMaterialTreasureApp;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.PayResult;
import com.youjuke.optimalmaterialtreasure.retrofit.RequestBean;
import com.youjuke.optimalmaterialtreasure.retrofit.ResponseBean;
import com.youjuke.optimalmaterialtreasure.retrofit.RetrofitManager;
import com.youjuke.optimalmaterialtreasure.retrofit.api.CommonService;
import com.youjuke.optimalmaterialtreasure.utils.InputMoney;
import com.youjuke.optimalmaterialtreasure.wxapi.WxPayEntity;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 描述: 充值页面
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-15 16:23
 */
public class RechargeableActivity  extends BaseActivity implements View.OnClickListener{

    private AppCompatEditText etRechargeableNum;
    private Toolbar mPayWayToolbar;
    private View mTakeOrderRules;
    private LinearLayout mLlWeiXinPay;
    private ImageView mImgRadioWx;
    private LinearLayout mLlAliPay;
    private ImageView mImgRadioZfb;
    private Button mBtnSureToPay;
    private String order_id;
    private String type;//1为一元夺宝，默认是0
    private IWXAPI iwxapi;
    private int payWay = 0;
    private static final int SDK_PAY_FLAG = 1;


    private void assignViews() {
        mPayWayToolbar = (Toolbar) findViewById(R.id.pay_way_toolbar);
        mTakeOrderRules = findViewById(R.id.take_order_rules);
        mLlWeiXinPay = (LinearLayout) findViewById(R.id.ll_wei_xin_pay);
        mImgRadioWx = (ImageView) findViewById(R.id.img_radio_wx);
        mLlAliPay = (LinearLayout) findViewById(R.id.ll_ali_pay);
        mImgRadioZfb = (ImageView) findViewById(R.id.img_radio_zfb);
        mBtnSureToPay = (Button) findViewById(R.id.btn_sure_to_pay);
        etRechargeableNum = (AppCompatEditText) findViewById(R.id.et_rechargeable_num);
        etRechargeableNum.setFilters(new InputFilter[]{new InputMoney(etRechargeableNum)});
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        assignViews();
        mLlWeiXinPay.setOnClickListener(this);
        mLlAliPay.setOnClickListener(this);
        mBtnSureToPay.setOnClickListener(this);
        iwxapi = WXAPIFactory.createWXAPI(RechargeableActivity.this, null);
        iwxapi.registerApp(OptimalMaterialTreasureApp.WX_PAY_API_KEY);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rechargeable;
    }


    @Override
    public void initToolBar() {
        mPayWayToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wei_xin_pay:
                mImgRadioWx.setImageResource(R.mipmap.btn_xz);
                mImgRadioZfb.setImageResource(R.mipmap.btn_wxz);
                payWay = 0;
                break;
            case R.id.ll_ali_pay:
                mImgRadioWx.setImageResource(R.mipmap.btn_wxz);
                mImgRadioZfb.setImageResource(R.mipmap.btn_xz);
                payWay = 1;
                break;
            case R.id.btn_sure_to_pay:
                if (etRechargeableNum.getText().toString().isEmpty()){
                    ToastUtil.show(this, "请输入充值金额");
                    break;
                }

                if (payWay == 1) {
                    aliPay();
                } else if (payWay == 0) {
                    if (!iwxapi.isWXAppInstalled()) {
                        ToastUtil.show(this, "没有安装微信");
                    } else if (!iwxapi.isWXAppSupportAPI()) {
                        ToastUtil.show(this, "当前版本不支持支付功能");
                    } else {
                        wxPay();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 微信支付
     */
    private void wxPay() {
        params.clear();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        params.put("mobile", OptimalMaterialTreasureApp.user.getMobile());
        params.put("payTool", 3);// 支付工具 2-支付宝 3-微信 必填
        params.put("money", etRechargeableNum.getText().toString());
        compositeSubscription.add(RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg("balance_recharge", params).toJson())
                .compose(this.<ResponseBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<ResponseBean>() {
                    @Override
                    public void call(ResponseBean responseBean) {
                        if ("200".equals(responseBean.getStatus())) {

                            L.d("微信支付请求订单", "" + responseBean.getData());
                            WxPayEntity wxPay = gson.fromJson(responseBean.getData(),WxPayEntity.class);
                            PayReq payReq = new PayReq();
                            payReq.appId = wxPay.getAppid();
                            L.i("appId=" + wxPay.getAppid());
                            payReq.partnerId = wxPay.getPartnerid();
                            L.i("partnerId=" + wxPay.getPartnerid());
                            payReq.prepayId = wxPay.getPrepayid();
                            L.d("prepayId=" + wxPay.getPrepayid());
                            payReq.nonceStr = wxPay.getNoncestr();
                            L.d("nonceStr=" + wxPay.getNoncestr());
                            payReq.timeStamp = wxPay.getTimestamp()+"";
                            L.d("timeStamp=" + wxPay.getTimestamp());
                            payReq.packageValue = wxPay.getPackageX();
                            L.d("packageValue=" + wxPay.getPackageX());
                            payReq.sign = wxPay.getSign();
                            // payReq.sign=appSign(wxPay);
                            L.d("sign=" + wxPay.getSign());
                            // payReq.extData=wxPay.getPay_info().getTrade_type();
                            Boolean b = iwxapi.sendReq(payReq);
                            ToastUtil.show(RechargeableActivity.this,"正在请求微信支付",5000);
                            L.d("微信开始支付请求" + b);
                        } else if ("400".equals(responseBean.getStatus())) {
                            ToastUtil.show(RechargeableActivity.this, "" + responseBean.getMessage(), 5000);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        L.d(throwable.toString());
                        myDialog.dismiss();
                        ToastUtil.show(RechargeableActivity.this, "请求失败", 5000);
                    }
                })
        );

    }
//    /**
//     * 微信支付签名算法sign
//     *
//     * @param parameters
//     * @return
//     */
//    public static String createSign(SortedMap<Object, Object> parameters) {
//
//        StringBuffer sb = new StringBuffer();
//        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
//        Iterator it = es.iterator();
//        while (it.hasNext()) {
//            @SuppressWarnings("rawtypes")
//            Map.Entry entry = (Map.Entry) it.next();
//            String k = (String) entry.getKey();
//            Object v = entry.getValue();
//            if (null != v && !"".equals(v) && !"sign".equals(k)
//                    && !"key".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        sb.append("key=w8ZZhWQfdpJRoMuMqKsupxtWVTSB4Zxs"); //KEY是商户秘钥
//        String sign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//        return sign;
//
//    }
//
//
//    private String appSign(WxPayEntity payEntity){
//        // 把参数的值传进去SortedMap集合里面
//        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
//        parameters.put("appid", payEntity.getPay_info().getAppid());
//        parameters.put("noncestr", payEntity.getPay_info().getNoncestr());
//        parameters.put("package", payEntity.getPay_info().getPackageX());
//        parameters.put("partnerid",payEntity.getPay_info().getPartnerid());
//        parameters.put("prepayid", payEntity.getPay_info().getPrepayid());
//        parameters.put("timestamp",payEntity.getPay_info().getTimestamp());
//        String sign= createSign(parameters);
//
//        L.d("我的sign"+sign);
//        return sign;
//    }

    //支付宝支付
    public void aliPay() {
        params.clear();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        params.put("mobile", OptimalMaterialTreasureApp.user.getMobile());
        params.put("payTool", 2);// 支付工具 2-支付宝 3-微信 必填
        params.put("money", etRechargeableNum.getText().toString());
        Subscription subscribe = RetrofitManager.getInstance().create(CommonService.class)
                .getData(new RequestBean.JsonMsg("balance_recharge", params).toJson())
                .compose(this.<ResponseBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.show(RechargeableActivity.this, "请求失败", 5000);
                    }

                    @Override
                    public void onNext(final ResponseBean responseBean) {
                        L.d("支付宝返回参数:"+responseBean.getData());
                        if ("200".equals(responseBean.getStatus())) {
                           // final PayOrderInfo payOrderInfo = gson.fromJson(responseBean.getData(), PayOrderInfo.class);
                            Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(RechargeableActivity.this);
                                    //L.i("字符串：" + payOrderInfo.getAlipay_params());
                                    String result = alipay.pay(responseBean.getData(), true);
                                    Log.i("msp 支付结果", result);

                                    Message msg = new Message();
                                    msg.what = SDK_PAY_FLAG;
                                    msg.obj = result;
                                    mHandler.sendMessage(msg);
                                }
                            };
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else if ("400".equals(responseBean.getStatus())) {
                            ToastUtil.show(RechargeableActivity.this, "" + responseBean.getMessage(), 5000);
                        }
                    }
                });
        compositeSubscription.add(subscribe);
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    Log.i("msp 支付结果 resultInfo", resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    Log.i("msp 支付结果 resultStatus", resultStatus);
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        //Toast.makeText(RechargeableActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
//                       // finish();
//
//                    } else {
//                        // 判断resultStatus 为非"9000"则代表可能支付失败
//                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
//                        if (TextUtils.equals(resultStatus, "8000")/*||TextUtils.equals(resultStatus, "6004")*/) {
//                            Toast.makeText(RechargeableActivity.this, "充值结果确认中", Toast.LENGTH_SHORT).show();
//                        } else if (TextUtils.equals(resultStatus, "6001")) {
//                            Toast.makeText(RechargeableActivity.this, "充值已取消", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Toast.makeText(RechargeableActivity.this, "充值失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                    Intent intent=new Intent(RechargeableActivity.this,PaySuccessActivity.class);
                    intent.putExtra("payResult",resultStatus);
                    startActivity(intent);
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
