//package com.dhruva.lock.services;
//
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.CountDownTimer;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//import androidx.core.app.NotificationCompat;
//
//import com.dhruva.lock.activities.main.locktaskMode;
//import com.dhruva.lock.activities.setting.LockSettingActivity;
//import com.dhruva.lock.base.AppConstants;
//import com.dhruva.lock.utils.NotificationHelper;
//import com.dhruva.lock.utils.SpUtil;
//
//
//public class CountdownTimer extends Service {
//
//    private String TAG = "CountdownTimer Service";
//    public static final String COUNTDOWN_BR = "com.example.backgoundtimercount";
//    Intent intent = new Intent(COUNTDOWN_BR);
//    CountDownTimer countDownTimer = null;
//    SharedPreferences sharedPreferences;
//    private boolean mTimerRunning;
//    private static final long START_TIME_IN_MILLIS = 40000;
//    private long millis = START_TIME_IN_MILLIS;
//    int id;
//
//
//    @Nullable
//    @Override
//    public void onCreate(){
//        super.onCreate();
//        sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
//        long millis = sharedPreferences.getLong("time",START_TIME_IN_MILLIS);
//
//        if (millis / 1000 == 0) {
//            millis = START_TIME_IN_MILLIS;
//        }
//        countdownTimer();
//        notification();
////        NotificationUtil.CountdownNotification(this, "CountdownTimer", "CountdownTimer is running");
//
//
//    }
//    private void notification() {
//        NotificationHelper notificationHelper = new NotificationHelper(this,intent);
//        NotificationCompat.Builder nb = notificationHelper.notificationCountdownt("Countdown","CountdownTimer is running");
//        startForeground(123, nb.build());
//    }
//    public int onStartCommand(Intent intent,int flags, int startId){
//        Log.i(TAG,"Starting timer...");
//        String nhankey = intent.getExtras().getString("countdown");
//        Log.e("CountdownTimer nhankey", nhankey);
//        if(nhankey.equals("on")) {
//            id =1 ;
//        }else if(nhankey.equals("off")){
//            id= 0;
//        }
//        if(id==1){
//            countDownTimer.start();
//            id=0;
//        }
//        else if(id == 0){
//            onDestroy();
//            resetTimer();
//        }
//
//        return START_NOT_STICKY;
//        //  return super.onStartCommand(intent, flags, startId);
//    }
//
////    private void notification() {
////        NotificationHelper notificationHelper = new NotificationHelper(this,intent);
////        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
////        //notificationHelper.getManager().notify(123, nb.build());
////        startForeground(123, nb.build());
////    }
//
//    private void countdownTimer() {
//        countDownTimer = new CountDownTimer(millis,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                millis =  millisUntilFinished;
//                Log.i(TAG,"Countdown seconds remaining:" + millisUntilFinished / 1000);
//                intent.putExtra("countdown",millisUntilFinished);
//                sendBroadcast(intent);
//            }
//
//            @Override
//            public void onFinish() {
//                Log.e("onFinish","Toi da finish");
//                mTimerRunning = false;
//                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.cancelAll();
//                CountdownTimer.this.stopSelf();
//                Intent myIntent = new Intent(CountdownTimer.this, locktaskMode.class);
//                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(myIntent);
//
//            }
//        };
//        countDownTimer.start();
//        mTimerRunning = true;
//    }
//    private void pauseTimer() {
//        countDownTimer.cancel();
//        Log.e(TAG,"toi da pause");
//        mTimerRunning = false;
//    }
//
//    private void resetTimer() {
//        millis = START_TIME_IN_MILLIS;
//    }
//    @Override
//    public void onDestroy() {
//        countDownTimer.cancel();
//        super.onDestroy();
//        Log.e(TAG,"toi da destroy");
//        mTimerRunning = false;
//    }
//    @Override
//    public IBinder onBind(Intent intent) {
////        throw new UnsupportedOperationException("Not yet implemented");
//        Log.e(TAG, "Service onBind");
//        return null;
//        //      return binder;
//    }
//
//
////    public boolean onUnbind(Intent intent){
////        Log.e(TAG, "Service onUnbind");
////        return super.onUnbind(intent);
////    }
//}
//
//
//
