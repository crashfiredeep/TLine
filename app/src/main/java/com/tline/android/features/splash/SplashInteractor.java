package com.tline.android.features.splash;


import com.tline.android.app.interactor.BaseInteractor;

public interface SplashInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    boolean isSplashDone();

    void setSpalshDone();
}