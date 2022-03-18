package com.PVD.NowSleep.widget;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.PVD.NowSleep.utils.ToastUtil;

public class DeviceAdminService extends DeviceAdminReceiver {
//    private String TAG = "DeviceAdminService";
//    void showLog(String msg) {
//        String status =  msg;
//        //  Logger.d(status);
//        Log.d(TAG,status);
//    }

    public static ComponentName getComponentName(Context context) {
        return new ComponentName(context.getApplicationContext(), DeviceAdminReceiver.class);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {

        ToastUtil.showToast("Device Admin Enabled");

    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Do you want to disable device admin?";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {

        ToastUtil.showToast("Device Admin Disabled");

    }

    @Override
    public void onLockTaskModeEntering(Context context, Intent intent, String pkg) {
        ToastUtil.showToast("KIOSK mode enabled");
    }
    @Override
    public void onLockTaskModeExiting(Context context, Intent intent) {
        ToastUtil.showToast("KIOSK mode disabled");
    }
}
