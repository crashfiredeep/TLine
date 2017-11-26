package com.tline.android.features.splash;

import com.tline.android.app.presenter.BasePresenter;

public interface SplashPresenter extends BasePresenter<SplashView> {

    void startLoading();

    void stopLoading();

    void doSplash();

    void checkNetwork();

    void launchNextActivity();
}