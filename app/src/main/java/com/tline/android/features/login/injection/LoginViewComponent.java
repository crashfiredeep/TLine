package com.tline.android.features.login.injection;

import com.tline.android.app.injection.ActivityScope;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.features.login.view.impl.LoginActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = LoginViewModule.class)
public interface LoginViewComponent {
    void inject(LoginActivity activity);
}