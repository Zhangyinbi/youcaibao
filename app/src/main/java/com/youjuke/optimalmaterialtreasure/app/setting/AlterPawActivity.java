package com.youjuke.optimalmaterialtreasure.app.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.library.utils.L;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.optimalmaterialtreasure.OptimalMaterialTreasureApp;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.login.LoginActivity;
import com.youjuke.optimalmaterialtreasure.retrofit.ApiContstants;
import com.youjuke.optimalmaterialtreasure.retrofit.RequestBean;
import com.youjuke.optimalmaterialtreasure.retrofit.ResponseBean;
import com.youjuke.optimalmaterialtreasure.retrofit.RetrofitManager;
import com.youjuke.optimalmaterialtreasure.retrofit.api.CommonService;
import com.zhy.autolayout.AutoLinearLayout;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：修改密码界面
 * author：zyb
 * Created by Administrator on 2017/2/17.
 */

public class AlterPawActivity extends BaseActivity implements View.OnClickListener {
    int type;
    private TextView tv_title;
    private ImageView iv_back;
    private EditText et_login_paw;
    private EditText et_old_paw;
    private EditText et_new_paw;
    private EditText et_repeat_paw;
    private Button btn_alter;
    private AutoLinearLayout all;

    @Override
    public void initViews(Bundle savedInstanceState) {
        type = getIntent().getIntExtra("type", 0);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        btn_alter = (Button) findViewById(R.id.btn_alter);
        btn_alter.setOnClickListener(this);
        all = (AutoLinearLayout) findViewById(R.id.all);

        et_login_paw = (EditText) findViewById(R.id.et_login_paw);
        et_old_paw = (EditText) findViewById(R.id.et_old_paw);
        et_new_paw = (EditText) findViewById(R.id.et_new_paw);
        et_repeat_paw = (EditText) findViewById(R.id.et_repeat_paw);
        if (type == 0) {
            tv_title.setText("修改登录密码");
        } else {
            et_new_paw.setInputType(InputType.TYPE_CLASS_NUMBER); //输入类型
            et_new_paw.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)}); //最大输入长度
            et_new_paw.setTransformationMethod(PasswordTransformationMethod.getInstance()); //设置为密码输入框
            et_repeat_paw.setInputType(InputType.TYPE_CLASS_NUMBER); //输入类型
            et_repeat_paw.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)}); //最大输入长度
            et_repeat_paw.setTransformationMethod(PasswordTransformationMethod.getInstance()); //设置为密码输入框
            tv_title.setText("修改支付密码");
            all.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_alter_paw;
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
            case R.id.btn_alter:
                String loginPaw = et_login_paw.getText().toString().trim();
                String oldPaw = et_old_paw.getText().toString().trim();
                String newPaw = et_new_paw.getText().toString().trim();
                String repeatPaw = et_repeat_paw.getText().toString().trim();
                if (type == 1 && TextUtils.isEmpty(loginPaw)) {
                    ToastUtil.show(this, "登录密码不能为空");
                    break;
                } else if (TextUtils.isEmpty(oldPaw)) {
                    ToastUtil.show(this, "原始密码不能为空");
                    break;
                } else if (TextUtils.isEmpty(newPaw)) {
                    ToastUtil.show(this, "新密码不能为空");
                    break;
                } else if (TextUtils.isEmpty(repeatPaw)) {
                    ToastUtil.show(this, "请再次输入新密码");
                    break;
                } else if (!newPaw.equals(repeatPaw)) {
                    ToastUtil.show(this, "新密码输入不一致");
                    break;
                }
                if (type == 1) {
                    if (newPaw.length() != 6) {
                        ToastUtil.show(this, "支付密码必须为6位数字");
                        break;
                    }
                    alterPayPaw(loginPaw, oldPaw, newPaw, repeatPaw);
                } else {
                    alterLoginPaw(oldPaw, newPaw, repeatPaw);
                }
                break;
        }
    }

    private void alterLoginPaw(String oldPaw, String newPaw, final String repeatPaw) {
        myDialog.show();
        params.clear();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        params.put("password", oldPaw);
        params.put("new_password", newPaw);
        params.put("new_password2", repeatPaw);
        compositeSubscription.add(
                RetrofitManager.getInstance().create(CommonService.class)
                        .getData(new RequestBean.JsonMsg(ApiContstants.CHANGE_PASSWORD, params).toJson())
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
                                e.printStackTrace();
                                myDialog.dismiss();
                                ToastUtil.show(AlterPawActivity.this, "修改失败");
                            }

                            @Override
                            public void onNext(ResponseBean responseBean) {
                                L.i("Data:" + responseBean.getData());
                                if ("200".equals(responseBean.getStatus())) {
                                    ActivityManager.getInstance().finishActivity();
                                    startActivity(new Intent(AlterPawActivity.this, LoginActivity.class));
                                    ToastUtil.show(AlterPawActivity.this, responseBean.getMessage());
                                } else if ("400".equals(responseBean.getStatus())) {
                                    ToastUtil.show(AlterPawActivity.this, responseBean.getMessage());
                                }
                            }
                        })
        );
    }

    private void alterPayPaw(String loginPaw, String oldPaw, String newPaw, String repeatPaw) {
        myDialog.show();
        params.clear();
        params.put("uid", OptimalMaterialTreasureApp.user.getId());
        params.put("token", OptimalMaterialTreasureApp.user.getToken());
        params.put("password", loginPaw);
        params.put("paypwd", oldPaw);
        params.put("new_paypwd", newPaw);
        params.put("new_paypwd2", repeatPaw);
        compositeSubscription.add(
                RetrofitManager.getInstance().create(CommonService.class)
                        .getData(new RequestBean.JsonMsg(ApiContstants.CHANGE_PAYPWD, params).toJson())
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
                                e.printStackTrace();
                                myDialog.dismiss();
                                ToastUtil.show(AlterPawActivity.this, "修改失败");
                            }

                            @Override
                            public void onNext(ResponseBean responseBean) {
                                L.i("Data:" + responseBean.getData());
                                if ("200".equals(responseBean.getStatus())) {
                                    ToastUtil.show(AlterPawActivity.this, responseBean.getMessage());
                                    ActivityManager.getInstance().finishActivity();
                                } else if ("400".equals(responseBean.getStatus())) {
                                    ToastUtil.show(AlterPawActivity.this, responseBean.getMessage());
                                }
                            }
                        })
        );
    }
}
