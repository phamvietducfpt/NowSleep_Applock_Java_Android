package com.PVD.NowSleep.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.services.BackgroundManager;
import com.PVD.NowSleep.services.LockService;
import com.PVD.NowSleep.utils.SpUtil;

public class AlarmFinish extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
                        SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE,false);
                BackgroundManager.getInstance().init(context).stopService(LockService.class);
                BackgroundManager.getInstance().init(context).stopAlarmManager();
                Intent i = new Intent();
                i.setAction("com.hello.action");
                context.sendBroadcast(i);

    }
}
