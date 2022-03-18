package com.PVD.NowSleep.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.services.BackgroundManager;
import com.PVD.NowSleep.services.LoadAppListService;
import com.PVD.NowSleep.services.LockService;
import com.PVD.NowSleep.utils.LogUtil;
import com.PVD.NowSleep.utils.SpUtil;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(@NonNull Context context, Intent intent) {
        LogUtil.i("Boot service....");
        //TODO: pie compatable done
       // context.startService(new Intent(context, LoadAppListService.class));
        BackgroundManager.getInstance().init(context).startService(LoadAppListService.class);
        if (SpUtil.getInstance().getBoolean(AppConstants.LOCK_STATE, false)) {
            BackgroundManager.getInstance().init(context).startService(LockService.class);
            BackgroundManager.getInstance().init(context).startAlarmManager();
        }
    }
}
