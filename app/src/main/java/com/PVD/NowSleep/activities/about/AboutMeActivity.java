package com.PVD.NowSleep.activities.about;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.PVD.NowSleep.R;
import com.PVD.NowSleep.base.BaseActivity;



public class AboutMeActivity extends BaseActivity {
    private ImageView btnBack;
    @Override
    public int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        btnBack = findViewById(R.id.btn_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
