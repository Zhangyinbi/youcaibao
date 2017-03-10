package com.youjuke.optimalmaterialtreasure.app;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.rxbus.annotation.Subscribe;
import com.youjuke.library.rxbus.annotation.Tag;
import com.youjuke.library.rxbus.thread.EventThread;
import com.youjuke.library.utils.SPUtil;
import com.youjuke.library.utils.ToastUtil;
import com.youjuke.optimalmaterialtreasure.OptimalMaterialTreasureApp;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.goods.GoodsFragment;
import com.youjuke.optimalmaterialtreasure.app.home.HomeFragment;
import com.youjuke.optimalmaterialtreasure.app.login.LoginActivity;
import com.youjuke.optimalmaterialtreasure.app.me.MeFragment;
import com.youjuke.optimalmaterialtreasure.app.order.OrderFragment;
import com.youjuke.optimalmaterialtreasure.app.site.SiteFragment;
import com.youjuke.optimalmaterialtreasure.entity.User;
import com.youjuke.optimalmaterialtreasure.updata.UpdateManager;
import com.youjuke.optimalmaterialtreasure.weights.FragmentTabHost;
import com.youjuke.optimalmaterialtreasure.weights.SweetAlertDialog;
import com.zhy.autolayout.AutoRelativeLayout;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;


/**
 * author：zyb
 * 描述：主activity
 */
public class MainActivity extends BaseActivity {

    private FrameLayout framelayout;
    public static int first = 0;
    public static int second = 0;
    private UpdateManager updateManager;

    public FragmentTabHost getTabHost() {
        return tabHost;
    }

    public void setTabHost(FragmentTabHost tabHost) {
        this.tabHost = tabHost;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    private int current;
    private FragmentTabHost tabHost;
    private Class[] fragments = new Class[]{HomeFragment.class, OrderFragment.class, SiteFragment.class, GoodsFragment.class, MeFragment.class};
    private int[] imgs = new int[]{R.drawable.home_selector, R.drawable.order_selector, R.drawable.site_selector, R.drawable.goodscar_selector, R.drawable.me_selector};
    private String[] mPageName = new String[]{"首页", "订单", "工地", "购物车", "我的"};
    private boolean isLoadingStatus = false;
    private AutoRelativeLayout main;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public void initViews(Bundle savedInstanceState) {
        autoLogin();
        PermissionGen.needPermission(this, 100, PERMISSIONS_STORAGE);
        main = (AutoRelativeLayout) findViewById(R.id.activity_main);
        framelayout = (FrameLayout) findViewById(R.id.realTabContent);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        tabHost.setup(this, getSupportFragmentManager(), R.id.realTabContent);
        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mPageName[i]);
            View view = getLayoutInflater().inflate(R.layout.tab_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
            imageView.setImageResource(imgs[i]);
            TextView textView = (TextView) view.findViewById(R.id.tv_title);
            textView.setText(mPageName[i]);
            tabSpec.setIndicator(view);
            tabHost.addTab(tabSpec, fragments[i], null);
        }
        tabHost.setCurrentTab(0);      //设置主界面（优先选中）
        tabClickListener();
//        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this,0,null);
    }

    int selectTab;//去登陆前选择的tab

    private void tabClickListener() {
        tabHost.getTabWidget().getChildTabViewAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtil.setColor(MainActivity.this, Color.parseColor(getString(com.youjuke.library.R.string.theme_color)), 0);
                selectTab = 0;
                tabHost.setCurrentTab(0);
            }
        });
        tabHost.getTabWidget().getChildTabViewAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implTabClickFunction(1);
            }
        });
        tabHost.getTabWidget().getChildTabViewAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implTabClickFunction(2);
            }
        });
        tabHost.getTabWidget().getChildTabViewAt(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implTabClickFunction(3);
            }
        });
        tabHost.getTabWidget().getChildTabViewAt(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implTabClickFunction(4);
            }
        });
    }

    private void implTabClickFunction(int index) {
        selectTab = index;
        isLoadingStatus = (boolean) SPUtil.get(getApplicationContext(), "isLoadingStatus", false);
        if (!isLoadingStatus) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, 1);
        } else {
            if (index == 4) {
                StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#518ced"), 0);
            } else {
                StatusBarUtil.setColor(MainActivity.this, Color.parseColor(getString(com.youjuke.library.R.string.theme_color)), 0);
            }
            tabHost.setCurrentTab(index);
        }
    }

    /**
     * 检测更新
     */
    public void checkUpdate() {
        updateManager = UpdateManager.getInstance(this);
        updateManager.checkUpdate(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == StoragePermissionUtil.REQUEST_EXTERNAL_STORAGE) {
//            Log.e("---------", "onRequestPermissionsResult: " + grantResults.length);
//            if (grantResults.length == 0) {
//                ToastUtil.show(MainActivity.this, "请去设置里面开启SD卡权限，否则将无法正常使用");
//                return;
//            }
//            if (grantResults[0] != PERMISSION_GRANTED) {
//                SweetAlertDialog.Builder builder = new SweetAlertDialog.Builder(this);
//                builder.setCancelable(false)
//                        .setMessage("请前往设置开启读写SD卡权限")
//                        .setTitle("提示")
//                        .setCancelableoutSide(false)
//                        .setNegativeButton("取消", new SweetAlertDialog.OnDialogClickListener() {
//                            @Override
//                            public void onClick(Dialog dialog, int which) {
//                                ToastUtil.show(MainActivity.this, "请去设置里面开启SD卡权限，否则将无法正常使用");
//                            }
//                        })
//                        .setPositiveButton("设置", new SweetAlertDialog.OnDialogClickListener() {
//                            @Override
//                            public void onClick(Dialog dialog, int which) {
//                                Intent intent = getAppDetailSettingIntent(MainActivity.this);
//                                startActivity(intent);
//                            }
//                        })
//                        .onDIsmissListener(new SweetAlertDialog.OnDialogClickListener() {
//                            @Override
//                            public void onClick(Dialog dialog, int which) {
//                                checkUpdate();
//                            }
//                        }).show();
//            } else {
//                checkUpdate();
//            }
//        }
    }

    private Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething() {
        checkUpdate();
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething() {
        SweetAlertDialog.Builder builder = new SweetAlertDialog.Builder(this);
        builder.setCancelable(false)
                .setMessage("请前往设置开启读写SD卡权限")
                .setTitle("提示")
                .setCancelableoutSide(false)
                .setNegativeButton("取消", new SweetAlertDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        ToastUtil.show(MainActivity.this, "请去设置里面开启SD卡权限，否则将无法正常使用");
                    }
                })
                .setPositiveButton("设置", new SweetAlertDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                        Intent intent = getAppDetailSettingIntent(MainActivity.this);
                        startActivity(intent);
                    }
                })
                .onDIsmissListener(new SweetAlertDialog.OnDialogClickListener() {
                    @Override
                    public void onClick(Dialog dialog, int which) {
                                checkUpdate();
                    }
                }).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (first == 1) {
            StatusBarUtil.setColor(MainActivity.this, Color.parseColor(getString(com.youjuke.library.R.string.theme_color)), 0);
            tabHost.setCurrentTab(0);
        } else if (second == 2) {
            StatusBarUtil.setColor(MainActivity.this, Color.parseColor(getString(com.youjuke.library.R.string.theme_color)), 0);
            tabHost.setCurrentTab(1);
            second = 0;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (selectTab == 4) {
                    StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#518ced"), 0);
                }
                tabHost.setCurrentTab(selectTab);
            } else {
                tabHost.setCurrentTab(0);
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar() {

    }

    /**
     * 自动登录
     */
    private void autoLogin() {
        User user = SPUtil.getObject(this, "user", User.class);
        if (user == null) {
            user = new User("", -1, "", "1321212sdas", "");
        }
        OptimalMaterialTreasureApp.user = user;
    }

    boolean isExit;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    isExit = false;
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

           /* if (updateManager!=null&&updateManager.getNoticeDialog()!=null&&updateManager.getNoticeDialog().isShowing()){
                ToastUtil.show(this,"请点击更新，否则将退出App");
                return true;
            }else*/
            if (!isExit) {
                ToastUtil.show(this, "再点一次退出优材宝App");
                handler.sendEmptyMessageDelayed(0, 3000);
                isExit = true;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 切换
     *
     * @param result
     */
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag("MainActivity.tabHost")
            })
    public void goShoppingCartNext(Integer result) {

        tabHost.setCurrentTab(result);
    }

}
