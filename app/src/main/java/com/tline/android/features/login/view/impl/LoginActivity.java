package com.tline.android.features.login.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.features.login.injection.DaggerLoginViewComponent;
import com.tline.android.features.login.injection.LoginViewModule;
import com.tline.android.features.login.presenter.LoginPresenter;
import com.tline.android.features.login.view.LoginView;

import javax.inject.Inject;

public final class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {

    @Inject
    PresenterFactory<LoginPresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerLoginViewComponent.builder()
                .appComponent(parentComponent)
                .loginViewModule(new LoginViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);


    }
}
