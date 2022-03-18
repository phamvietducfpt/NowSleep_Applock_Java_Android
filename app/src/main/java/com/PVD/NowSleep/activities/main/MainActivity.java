package com.PVD.NowSleep.activities.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.PVD.NowSleep.R;
import com.PVD.NowSleep.activities.setting.LockSettingActivity;
import com.PVD.NowSleep.base.AppConstants;
import com.PVD.NowSleep.base.BaseActivity;
import com.PVD.NowSleep.model.CommLockInfo;
import com.PVD.NowSleep.mvp.contract.LockMainContract;
import com.PVD.NowSleep.mvp.p.LockMainPresenter;
import com.PVD.NowSleep.utils.SystemBarHelper;
import com.PVD.NowSleep.widget.DialogSearch;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends BaseActivity implements LockMainContract.View, View.OnClickListener {

    private static final int RESULT_ACTION_IGNORE_BATTERY_OPTIMIZATION = 351;
    private static final String TAG="MainActivity";
    private RelativeLayout mTopLayout;
    private ImageView btn_back;
    private TextView mEditSearch;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CommentPagerAdapter mPagerAdapter;
    private LockMainPresenter mLockMainPresenter;
    private DialogSearch mDialogSearch;
    private List<String> titles;
    private List<Fragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mEditSearch = findViewById(R.id.edit_search);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mTopLayout = findViewById(R.id.top_layout);
        mTopLayout.setPadding(0, SystemBarHelper.getStatusBarHeight(this), 0, 0);

        mLockMainPresenter = new LockMainPresenter(this, this);
        mLockMainPresenter.loadAppInfo(this);
        //
        btn_back = findViewById(R.id.btn_back);
    }

    @Override
    protected void initData() {
        mDialogSearch = new DialogSearch(this);
        PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (powerManager != null && !powerManager.isIgnoringBatteryOptimizations(AppConstants.APP_PACKAGE_NAME)) {
                @SuppressLint("BatteryLife")
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + AppConstants.APP_PACKAGE_NAME));
                startActivity(intent);
            }
        }
    }


    @Override
    protected void initAction() {
        mEditSearch.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        mDialogSearch.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mLockMainPresenter.loadAppInfo(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_ACTION_IGNORE_BATTERY_OPTIMIZATION) {
            // do nothing::  make this required
        }
    }

    @Override
    public void loadAppInfoSuccess(@NonNull List<CommLockInfo> list) {
        int sysNum = 0;
        int userNum = 0;
        for (CommLockInfo info : list) {
            if (info.isSysApp()) {
                sysNum++;
            } else {
                userNum++;
            }
        }
        titles = new ArrayList<>();
        titles.add("System Apps" + " (" + sysNum + ")");
        titles.add("User Apps" + " (" + userNum + ")");

        SysAppFragment sysAppFragment = SysAppFragment.newInstance(list);
        UserAppFragment userAppFragment = UserAppFragment.newInstance(list);

        fragmentList = new ArrayList<>();
        fragmentList.add(sysAppFragment);
        fragmentList.add(userAppFragment);
        mPagerAdapter = new CommentPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.edit_search:
                mDialogSearch.show();
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    public class CommentPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragmentList ;
        private List<String> titles ;


        public CommentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
            super(fm);
            this.fragmentList = fragmentList;
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }
    }

}
