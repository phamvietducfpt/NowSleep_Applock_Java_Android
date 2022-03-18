package com.PVD.NowSleep.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.PVD.NowSleep.R;
import com.PVD.NowSleep.adapters.ViewPagerAdapter;
import com.PVD.NowSleep.base.BaseActivity;
import com.PVD.NowSleep.services.BackgroundManager;
import com.PVD.NowSleep.services.LockService;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends BaseActivity{

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//    }

    private ViewPager2 mviewPager2;
    private BottomNavigationView mbottomNavigationView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
      //  settime_page = findViewById(R.id.settime_page);
        mviewPager2 = findViewById(R.id.view_pager);
        mbottomNavigationView = findViewById(R.id.bottom_menu);
    }

    @Override
    protected void initData() {
//        if(!BackgroundManager.getInstance().init(this).isServiceRunning(LockService.class)){
//            BackgroundManager.getInstance().init(this).startService(LockService.class);
//        }
//        BackgroundManager.getInstance().init(this).startAlarmManager();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mviewPager2.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void initAction() {
//        settime_page.setOnClickListener(this);
        mbottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.HomeActivity){
                    mviewPager2.setCurrentItem(0);
                }else if(id == R.id.Brightness){
                    mviewPager2.setCurrentItem(1);
                }else if(id == R.id.Menubook){
                    mviewPager2.setCurrentItem(2);
                }else if(id == R.id.Setting){
                    mviewPager2.setCurrentItem(3);
                }
                return true;
            }
        });

        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.HomeActivity).setChecked(true);
                        break;
                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.Brightness).setChecked(true);
                        break;
                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.Menubook).setChecked(true);
                        break;
                    case 3:
                        mbottomNavigationView.getMenu().findItem(R.id.Setting).setChecked(true);
                        break;
                }
            }
        });
    }

}