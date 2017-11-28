package com.tline.android.features.splash;

import com.tline.android.app.injection.ActivityScope;
import com.tline.android.app.injection.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashViewModule.class)
public interface SplashViewComponent {
    void inject(SplashActivity activity);
}