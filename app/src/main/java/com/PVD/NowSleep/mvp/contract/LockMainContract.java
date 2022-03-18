package com.PVD.NowSleep.mvp.contract;

import android.content.Context;
import com.PVD.NowSleep.base.BasePresenter;
import com.PVD.NowSleep.base.BaseView;
import com.PVD.NowSleep.model.CommLockInfo;
import com.PVD.NowSleep.mvp.p.LockMainPresenter;

import java.util.List;



public interface LockMainContract {
    interface View extends BaseView<Presenter> {

        void loadAppInfoSuccess(List<CommLockInfo> list);
    }

    interface Presenter extends BasePresenter {
        void loadAppInfo(Context context);

        void searchAppInfo(String search, LockMainPresenter.ISearchResultListener listener);

        void onDestroy();
    }
}
