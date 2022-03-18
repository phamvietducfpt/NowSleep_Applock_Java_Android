package com.PVD.NowSleep.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.PVD.NowSleep.activities.main.BookFragment;
import com.PVD.NowSleep.activities.main.BrightnessFragment;
import com.PVD.NowSleep.activities.main.HomeFragment;
import com.PVD.NowSleep.activities.main.SettingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1:
                return new BrightnessFragment();
            case 2:
                return new BookFragment();
            case 3:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
