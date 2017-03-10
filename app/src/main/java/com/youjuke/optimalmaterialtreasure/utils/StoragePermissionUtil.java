package com.youjuke.optimalmaterialtreasure.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;


/**
 * 描述: android23+ 内存权限请求
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-03-08 10:02
 */

public class StoragePermissionUtil {
    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

//        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
//            ToastUtil.show(activity,"请前往应用管理授权此应用，否则会影响使用");
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//        }
    }
}
