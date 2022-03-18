package com.PVD.NowSleep.activities.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.PVD.NowSleep.R;
import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.base.BaseActivity;
import com.PVD.NowSleep.receiver.AlarmFinish;
import com.PVD.NowSleep.receiver.AlarmReceiver;
import com.PVD.NowSleep.services.BackgroundManager;
import com.PVD.NowSleep.services.LoadAppListService;
import com.PVD.NowSleep.services.LockService;
import com.PVD.NowSleep.utils.SpUtil;
import com.PVD.NowSleep.widget.TimePickerFragment;
import com.PVD.NowSleep.widget.TimePickerFragmentEnd;

import java.util.Calendar;
import java.util.Locale;

//import android.app.NotificationChannel;
//import android.app.NotificationManager;
/** import test **/
//import android.os.Handler;
//import android.os.ResultReceiver;
//import java.util.Date;
/** import test **/
public class SettimeActivity extends BaseActivity implements TimePickerDialog.OnTimeSetListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener  {

    private final static String TAG = "Settime_Activity";
    public static String DATEPICKER_TAG = "";
    private TextView TV_selectedtimeStart, TV_selectedtimeEnd,Countdowntimer, Back_activity;
    private Button Button_Cancel_Alarm;
    //Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;
    private boolean Time_Dialog;
    private SeekBar seekBar;
    private ConstraintLayout ManageApp;
    private CheckBox cbLockSwitch;
    private ImageView img_back;
    @Override
    public int getLayoutId() {
        return (R.layout.activity_settime);
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        cbLockSwitch=findViewById(R.id.checkbox_app_lock_on_off1);
        Countdowntimer = findViewById(R.id.Count_Down_Timer);
        TV_selectedtimeStart = findViewById(R.id.tv_selected_start);
        TV_selectedtimeEnd = findViewById(R.id.tv_selected_end);
        SeekBar seekBar = findViewById(R.id.seekBar);
        ManageApp = findViewById(R.id.btn_ManageApp);
        Back_activity = findViewById(R.id.Back_activity);
        img_back =findViewById(R.id.img_back);
    }

    @Override
    protected void initData() {

        boolean isLockOpen = SpUtil.getInstance().getBoolean(AppConstants.LOCK_STATE);
        cbLockSwitch.setChecked(isLockOpen);
    }

    @Override
    protected void initAction() {
        cbLockSwitch.setOnCheckedChangeListener(this);
        TV_selectedtimeStart.setOnClickListener(this);
        TV_selectedtimeEnd.setOnClickListener(this);
        ManageApp.setOnClickListener(this);
        Back_activity.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void goManageApp() {
        BackgroundManager.getInstance().init(this).startService(LoadAppListService.class);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        boolean Time_Dialog = SpUtil.getInstance().getBoolean(AppConstants.Alarm_Receiver);
        if(Time_Dialog == true){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            setAlarm(calendar);
            updateTimeText(calendar);
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            updateTimeTextEnd(calendar);
            setTimeFinish(calendar);
        }

    }
    private void updateTimeTextEnd(Calendar calendar) {
        String timeText = "";
        timeText += java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(calendar.getTime());
        TV_selectedtimeEnd.setText(timeText);
    }
    private void setAlarm(Calendar calendar ) {
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE , 1);
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("settime","on");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (alarmManager != null) {
            //bao dong 1Lan
//            SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE,true);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            //bao dong hang ngay
//                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                        AlarmManager.INTERVAL_DAY, pendingIntent);
        }
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, LockRestarterBroadcastReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 95374, intent, 0);
//        if (alarmManager != null) {
//            //bao dong 1Lan
//            SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE,true);
//            intent.putExtra("type", "startlockserviceFromAM");
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//
//            //bao dong hang ngay
////                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
////                        AlarmManager.INTERVAL_DAY, pendingIntent);
//        }
    }
    private void setTimeFinish(Calendar calendar) {
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE , 1);
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmFinish.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 2, intent, 0);
        if (alarmManager != null) {
            //bao dong 1Lan
            intent.putExtra("settime","off");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent2);
            //bao dong hang ngay
//                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                        AlarmManager.INTERVAL_DAY, pendingIntent);
        }

    }

//    private void cancelAlarm() {
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        if (intent != null) {
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//            alarmManager.cancel(pendingIntent);
//            intent.putExtra("countdown","off");
//            sendBroadcast(intent);
//            Log.i(TAG,"Toi di finish time in countdowntimer");
//        }
//        stopService(new Intent(this,CountdownTimer.class));
//        Log.i(TAG,"Stopped service");
//    }
    private void updateTimeText(Calendar c) {
        String timeText = "";
        timeText += java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(c.getTime());
        TV_selectedtimeStart.setText(timeText);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "toi da vao dc chinh sua brightness");
            try {
                //Get the current system brightness
                brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
//                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            } catch (Settings.SettingNotFoundException e) {
                //Throw an error case it couldn't be retrieved
                Log.e("Error", "Cannot access system brightness");
                e.printStackTrace();
            }
            //Set the system brightness using the brightness variable value
            Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, 15);
            //Get the current window attributes
            WindowManager.LayoutParams layoutpars = window.getAttributes();
            //Set the brightness of this window
            layoutpars.screenBrightness = 15 / (float) 255;
            //Apply attribute changes to this window
            window.setAttributes(layoutpars);

            //darkmode
            //          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        }
    };

    private BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent CDintent) {
            //Update GUI
            updateGUI(CDintent);
        }
    };
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver2,new IntentFilter(LockService.COUNTDOWN_BR));
        Log.i(TAG,"Registered broadcast receiver");
        Intent myIntent = new Intent(this, LockService.class);
//        bindService(myIntent, serviceConnection,Context.BIND_AUTO_CREATE);
//        Log.i(TAG, "on Start");
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver2);
        Log.i(TAG,"pause broadcast receiver");
    }
    @Override
    protected void onStop() {
        try {
            unregisterReceiver(broadcastReceiver2);
            Log.i(TAG,"stop broadcast receiver");
        } catch (Exception e) {
            // Receiver was probably already
        }
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(broadcastReceiver);
//        stopService(new Intent(this,CountdownTimer.class));
//        Log.i(TAG,"Stopped service");
    }
    private void updateGUI(Intent CDintent) {
        if (CDintent.getExtras() != null) {
            long millisUntilFinished = CDintent.getLongExtra("countdown",60000);
            Log.i(TAG,"Countdown seconds remaining:" + millisUntilFinished / 1000);
            int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
            int seconds = (int) (millisUntilFinished / 1000) % 60;

            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//            Countdowntimer.setText( Long.toString(millisUntilFinished / 1000));
            Countdowntimer.setText(timeLeftFormatted);
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
            sharedPreferences.edit().putLong("time",millisUntilFinished).apply();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_selected_start:
                DialogFragment newFragment3 = new TimePickerFragment();
                newFragment3.show(getSupportFragmentManager(), "timePicker start");
                break;
            case R.id.tv_selected_end:
                DialogFragment newFragment = new TimePickerFragmentEnd();
                newFragment.show(getSupportFragmentManager(), "timePicker end");
                break;
            case R.id.btn_ManageApp:
                goManageApp();
                break;
            case R.id.Back_activity:
            case R.id.img_back:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean b) {
        switch (buttonView.getId()) {
            case R.id.checkbox_app_lock_on_off1:
                SpUtil.getInstance().putBoolean(AppConstants.LOCK_STATE, b);
                if (b) {

                } else {
                    BackgroundManager.getInstance().init(SettimeActivity.this).stopService(LockService.class);
                    BackgroundManager.getInstance().init(SettimeActivity.this).stopAlarmManager();
                    Intent intent = new Intent(this, AlarmReceiver.class);
                    if (intent != null) {
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
                        alarmManager.cancel(pendingIntent);

                    }
                    Intent myintent = new Intent(this, AlarmFinish.class);
                    if (myintent != null) {
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, 0);
                        alarmManager.cancel(pendingIntent);
                    }
                }
                break;
        }
    }
}
