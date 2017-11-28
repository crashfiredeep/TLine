package com.tline.android.features.login.view;

import android.support.annotation.UiThread;

import com.tline.android.app.view.BaseView;

@UiThread
public interface LoginView extends BaseView{

    void updateUi();

    void launchHomeActivity();

    void showLoginError(String message);
}