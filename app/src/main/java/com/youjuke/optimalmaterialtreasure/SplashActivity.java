package com.youjuke.optimalmaterialtreasure;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.manager.ActivityManager;
import com.youjuke.optimalmaterialtreasure.app.MainActivity;

/**
 * 描述：启动页面
 * Created by Administrator on 2017/2/4.
 * author zyb
 */
public class SplashActivity extends BaseActivity {

    private ImageView im_splash;
    @Override
    public void initViews(Bundle savedInstanceState) {
        if (!isTaskRoot()) {
            finish();
            return;
        }
        im_splash = (ImageView) findViewById(R.id.im_splash);
        startAnimate();
    }

    @Override
    public int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    public void initToolBar() {

    }

    private void startAnimate() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        im_splash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                ActivityManager.getInstance().finishActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
