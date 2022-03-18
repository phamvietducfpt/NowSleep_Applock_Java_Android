package com.PVD.NowSleep.activities.main;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.PVD.NowSleep.R;
import com.PVD.NowSleep.base.BaseActivity;
import com.PVD.NowSleep.widget.DeviceAdminService;
import com.PVD.NowSleep.widget.KIOSKManager;

public class locktaskMode extends BaseActivity implements View.OnClickListener{

    DevicePolicyManager devicePolicyManager = null; // devicePolicyManager used to activate device admin
    ComponentName adminCompName = null;             // adminCompName
    private boolean status =true;

    @Override
    public int getLayoutId() {
        return (R.layout.activity_locktask_mode);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }
    @Override
    protected void initData() {
        initDeviceAdmin();
        IntentFilter filter = new IntentFilter();

        filter.addAction("com.hello.action");
        registerReceiver(receiver, filter);
    }
    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            activateKIOSK(false);
            finish();
        }
    };
    @Override
    protected void initAction() {
        activateKIOSK(status);

    }

    /**
     * Activates KIOSK mode by calling enableKioskMode() of KIOSKManager class
     *
     * @param status
     */
    private void activateKIOSK(boolean status){
        if(devicePolicyManager.isAdminActive(adminCompName)) {
            KIOSKManager km = new KIOSKManager(this);
            km.enableKioskMode(status);
        }
    }

    /**
     * Initialize device admin privileges
     */
    private void initDeviceAdmin(){
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminCompName = new ComponentName(this, DeviceAdminService.class);              // Initializing the component;

        if (devicePolicyManager.isDeviceOwnerApp(getPackageName())) {
            devicePolicyManager.setLockTaskPackages(adminCompName, new String[]{getPackageName()});
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Called when the window containing this view gains or loses focus and Hide system UIs
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activateKIOSK(false);
    }
    public void finish() {
        super.finish();

    };
//    @Override
//    public void onBackPressed(){
//
//    }
}
