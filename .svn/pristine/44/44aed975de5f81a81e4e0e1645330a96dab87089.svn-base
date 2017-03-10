package com.youjuke.optimalmaterialtreasure;

import com.youjuke.library.base.BaseApplication;
import com.youjuke.optimalmaterialtreasure.entity.User;
import com.youjuke.optimalmaterialtreasure.utils.CrashHandler;
import com.zhy.autolayout.config.AutoLayoutConifg;


/**
 * 描述:
 * <p>
 * 工程:
 * #0000    Tian Xiao    2016-11-24 14:07
 */
public class OptimalMaterialTreasureApp extends BaseApplication {
    public static User user = null;
    //微信支付
    public static final String WX_PAY_API_KEY = "51e0ef150cd9be3f52e06a907b122936";
    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
       /* JPushInterface.setDebugMode(true);//设置显示调试
        //极光推送
        JPushInterface.init(this);*/
    }

    @Override
    public void setDefaultUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));
    }

}
