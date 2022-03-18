package com.PVD.NowSleep.activities.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.PVD.NowSleep.R;
import com.PVD.NowSleep.activities.about.AboutMeActivity;
//import com.dhruva.lock.activities.lock.GestureCreateActivity;
import com.PVD.NowSleep.activities.lock.GestureCreateActivity;
import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.base.BaseActivity;
//import com.dhruva.lock.model.LockAutoTime;
import com.PVD.NowSleep.services.BackgroundManager;
import com.PVD.NowSleep.services.LockService;
import com.PVD.NowSleep.utils.SpUtil;
import com.PVD.NowSleep.utils.SystemBarHelper;
import com.PVD.NowSleep.utils.ToastUtil;



public class LockSettingActivity extends BaseActivity implements View.OnClickListener
        , DialogInterface.OnDismissListener, CompoundButton.OnCheckedChangeListener {

    public static final String ON_ITEM_CLICK_ACTION = "on_item_click_action";
    private static final int REQUEST_CHANGE_PWD = 3;
    private CheckBox cbLockScreen;
    private CheckBox cbHidePattern;
    private CheckBox cbVibration;

    private TextView tvAbout,
            tvChangePwd;

  //  private LockSettingReceiver mLockSettingReceiver;
   // private SelectLockTimeDialog dialog;
    private RelativeLayout mTopLayout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        cbLockScreen=findViewById(R.id.checkbox_lock_screen_switch_on_phone_lock);
        cbHidePattern=findViewById(R.id.checkbox_show_hide_pattern);
        cbVibration=findViewById(R.id.checkbox_vibrate);
        tvChangePwd = findViewById(R.id.btn_change_pwd);
        tvAbout = findViewById(R.id.about_me);

        //
        mTopLayout = findViewById(R.id.top_layout);
        mTopLayout.setPadding(0, SystemBarHelper.getStatusBarHeight(this), 0, 0);
    }

    @Override
    protected void initData() {
        boolean isLockAutoScreen = SpUtil.getInstance().getBoolean(AppConstants.LOCK_AUTO_SCREEN, false);
        cbLockScreen.setChecked(isLockAutoScreen);
        boolean isLockHidePattern = SpUtil.getInstance().getBoolean(AppConstants.LOCK_IS_HIDE_LINE, false);
        cbHidePattern.setChecked(isLockHidePattern);
    }

    @Override
    protected void initAction() {
        cbLockScreen.setOnCheckedChangeListener(this);
        cbHidePattern.setOnCheckedChangeListener(this);
        cbVibration.setOnCheckedChangeListener(this);
        tvChangePwd.setOnClickListener(this);
        tvAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.btn_change_pwd:
                Intent intent = new Intent(LockSettingActivity.this, GestureCreateActivity.class);
                startActivityForResult(intent, REQUEST_CHANGE_PWD);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.about_me:
                intent = new Intent(LockSettingActivity.this, AboutMeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean b) {
        switch (buttonView.getId()) {

            case R.id.checkbox_lock_screen_switch_on_phone_lock:
                SpUtil.getInstance().putBoolean(AppConstants.LOCK_AUTO_SCREEN, b);
                break;
            case R.id.checkbox_show_hide_pattern:
                SpUtil.getInstance().putBoolean(AppConstants.LOCK_IS_HIDE_LINE, b);
                break;
            case R.id.checkbox_vibrate:
                SpUtil.getInstance().putBoolean(AppConstants.PATTERN_VIBRATION,b);
                Toast.makeText(LockSettingActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CHANGE_PWD:
                    ToastUtil.showToast("Password reset succeeded");
                    break;
            }
        }
    }


    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
