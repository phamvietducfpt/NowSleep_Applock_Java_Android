package com.PVD.NowSleep.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.services.BackgroundManager;
import com.PVD.NowSleep.services.LockService;
import com.PVD.NowSleep.utils.SpUtil;

public class AlarmReceiver extends BroadcastReceiver {
    //    private CountdownTimer myService;
    private boolean isConnected;

    private final static String TAG = "AlarmReceiver BroadcastService";
    @Override
    public void onReceive(Context context, Intent intent) {
//        boolean lockState = SpUtil.getInstance().getBoolean(AppConstants.LOCK_STATE);
            String truyenkey = intent.getExtras().getString("settime");
//        if (truyenkey.equals("on")) {
//                SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE,true);
//                BackgroundManager.getInstance().init(context).stopService(LockService.class);
//                BackgroundManager.getInstance().init(context).startService(LockService.class);
//                BackgroundManager.getInstance().init(context).startAlarmManager();
//            }
//         if (truyenkey.equals("off")) {
//                SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE,false);
//                BackgroundManager.getInstance().init(context).stopService(LockService.class);
//                BackgroundManager.getInstance().init(context).stopAlarmManager();
//                Intent i = new Intent(context, locktaskMode.class);
//                i.setAction("com.hello.action");
//                context.sendBroadcast(i);
//            }
        SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE,true);
        BackgroundManager.getInstance().init(context).stopService(LockService.class);
        BackgroundManager.getInstance().init(context).startService(LockService.class);
        BackgroundManager.getInstance().init(context).startAlarmManager();
//        Intent i = new Intent(context, Settime_Activity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, i, 0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "vietduc")
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("NowSleep Notification")
//                .setContentText("Your phone will be control, please sleeping now <3")
//                .setAutoCancel(true)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setContentIntent(pendingIntent);
//            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
//            notificationManagerCompat.notify(123, builder.build());



//        Log.e(TAG, "CountdownTimer");
//        String truyenkey = intent.getExtras().getString("countdown");
//        if(truyenkey.equals("on")){
//            context.sendBroadcast(new Intent("INTERNET_LOST"));
//        }
//        Intent myIntent = new Intent(context, CountdownTimer.class);
//        Log.e("toi truyen key", truyenkey);
//        myIntent.putExtra("countdown",truyenkey);
//
//        context.startForegroundService(myIntent);






//        if(truyenkey.equals("off")){
//            if(isConnected =true ){
//                context.unbindService(serviceConnection);
//                isConnected = false;
//            }
//        }
        //context.startService(myIntent);
        // context.bindService(myIntent,serviceConnection, Context.BIND_AUTO_CREATE);
//        sharedPreferences = context.getSharedPreferences("Context",context.MODE_PRIVATE);
//        long millis = sharedPreferences.getLong("time",30000);
//        if (millis / 1000 == 0) {
//            millis = 30000;
//        }
//        countDownTimer = new CountDownTimer(millis,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//             //.setText("seconds remaining: " + millisUntilFinished / 1000);
//                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
//                intent.putExtra("countdown", millisUntilFinished);
//                context.sendBroadcast(i);
//            }
//
//            @Override
//            public void onFinish() {
//                //Countdowntimer.setText("done!");
//                Log.i(TAG, "Timer finished");
//            }
//        };countDownTimer.start();
    }


//    NotificationHelper notificationHelper = new NotificationHelper(context,intent);
//    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());

}