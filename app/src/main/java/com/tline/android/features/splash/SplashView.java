package com.tline.android.features.splash;

import android.support.annotation.UiThread;

import com.tline.android.app.view.BaseView;

@UiThread
public interface SplashView extends BaseView {

    void showLoading();

    void hideLoading();

    void finishActivity();

    void launchNextActivity();

    void showNetworkError();

}