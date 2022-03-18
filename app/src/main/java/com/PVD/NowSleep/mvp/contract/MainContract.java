package com.PVD.NowSleep.mvp.contract;

import android.content.Context;

import com.PVD.NowSleep.base.BasePresenter;
import com.PVD.NowSleep.base.BaseView;
import com.PVD.NowSleep.model.CommLockInfo;

import java.util.List;



public interface MainContract {
    interface View extends BaseView<Presenter> {
        void loadAppInfoSuccess(List<CommLockInfo> list);
    }

    interface Presenter extends BasePresenter {
        void loadAppInfo(Context context, boolean isSort);

        void loadLockAppInfo(Context context);

        void onDestroy();
    }
}
