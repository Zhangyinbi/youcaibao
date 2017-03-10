package com.youjuke.library.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.youjuke.library.R;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.library.rxbus.RxBus;
import com.youjuke.library.service.HideService;
import com.youjuke.library.weights.MyDialog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rx.subscriptions.CompositeSubscription;


/**
 * 描述:Acitivity基类
 * <p>
 * 工程:
 * #0000    Tian Xiao    2016-09-06 13:38
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    protected CompositeSubscription compositeSubscription;
    /**
     * 访问网络时 提交数据的集合
     */
    protected Map<String, Object> params = new HashMap<String, Object>();

    protected Gson gson = new Gson();
    public MyDialog myDialog = new MyDialog(this);
    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {//内存不足的时，app被释放之后，如果savedInstanceState被清空，这样就不会触发系统重建的恢复
            savedInstanceState.putParcelable("android:support:fragments", null);//清空保存Fragment的状态数据
        }
        super.onCreate(savedInstanceState);
        /**
         * 状态栏字体颜色,适配小米的MIUI、魅族的Flyme和Android6.0以上
         */
        boolean b = FitterSetStatusBarLightMode(getWindow(), true);
        if (!b) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        //设置布局内容
        setContentView(getLayoutId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        //初始化Dialog
        //初始化全局RX订阅者
        compositeSubscription = new CompositeSubscription();
        setStatusColor();
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();
        //添加进栈
        ActivityManager.getInstance().addActivity(this);
        RxBus.get().register(this);
        startHideService();

    }

    public void setStatusColor() {
        StatusBarUtil.setColor(this, Color.parseColor(getString(R.string.theme_color)), 0);
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
            myDialog = null;
        }
        if (compositeSubscription != null
                && !compositeSubscription.isUnsubscribed())
            compositeSubscription.unsubscribe();
        RxBus.get().unregister(this);
        ActivityManager.getInstance().finishActivity(this);
        stopHideService();
    }

    public abstract void initViews(Bundle savedInstanceState);

    public abstract int getLayoutId();

    public abstract void initToolBar();

    /**
     * 获取Intent
     */
    protected void handleIntent(Intent intent) {
    }

    /**
     * 隐藏软键盘
     **/
    public void hideSoftInputFromWindow(View v) {
        View mView = getWindow().peekDecorView();
        if (mView != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }


    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            v.getLocationInWindow(leftTop);
            int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                hideInputMethod(this, v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 设置状态栏字体图标为深色，需要小米的MIUI、魅族的Flyme或者Android6.0以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FitterSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                Class clazz = window.getClass();
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }

            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }





    /**
     * 开始预加载进程
     */
    private void startHideService() {
        Intent intent = new Intent(this, HideService.class);
        this.startService(intent);
    }

    private void stopHideService() {
        Intent intent = new Intent(this, HideService.class);
        this.stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
