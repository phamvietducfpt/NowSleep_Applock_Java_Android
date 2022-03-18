package com.PVD.NowSleep.activities.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.PVD.NowSleep.R;
import com.PVD.NowSleep.activities.about.AboutMeActivity;
import com.PVD.NowSleep.activities.lock.GestureCreateActivity;
import com.PVD.NowSleep.activities.setting.LockSettingActivity;
import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.utils.SpUtil;
import com.PVD.NowSleep.utils.SystemBarHelper;
import com.PVD.NowSleep.utils.ToastUtil;

public class SettingFragment extends Fragment implements  View.OnClickListener,CompoundButton.OnCheckedChangeListener  {
    public static final String ON_ITEM_CLICK_ACTION = "on_item_click_action";
    private static final int REQUEST_CHANGE_PWD = 3;
    private CheckBox cbLockScreen;
    private CheckBox cbHidePattern;
    private CheckBox cbVibration;

    private TextView tvAbout,
            tvChangePwd;

    //  private LockSettingReceiver mLockSettingReceiver;
    // private SelectLockTimeDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        tvChangePwd = v.findViewById(R.id.btn_change_pwd);
        cbVibration= v.findViewById(R.id.checkbox_vibrate);
        tvAbout = v.findViewById(R.id.about_me);
        cbHidePattern = v.findViewById(R.id.checkbox_show_hide_pattern);
        cbLockScreen = v.findViewById(R.id.checkbox_lock_screen_switch_on_phone_lock);

        boolean isLockAutoScreen = SpUtil.getInstance().getBoolean(AppConstants.LOCK_AUTO_SCREEN, false);
        cbLockScreen.setChecked(isLockAutoScreen);
        boolean isLockHidePattern = SpUtil.getInstance().getBoolean(AppConstants.LOCK_IS_HIDE_LINE, false);
        cbHidePattern.setChecked(isLockHidePattern);

        cbLockScreen.setOnCheckedChangeListener(this);
        cbHidePattern.setOnCheckedChangeListener(this);
        cbVibration.setOnCheckedChangeListener(this);
        tvChangePwd.setOnClickListener(this);
        tvAbout.setOnClickListener(this);
        return v;
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
                Toast.makeText(getActivity(), "Not implemented yet", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    @Override
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.btn_change_pwd:
                Intent intent = new Intent(getActivity(), GestureCreateActivity.class);
                startActivityForResult(intent, REQUEST_CHANGE_PWD);
                break;
            case R.id.about_me:
                intent = new Intent(getActivity(), AboutMeActivity.class);
                startActivity(intent);
                break;
        }
    }

}