package com.tline.android.app.injection;

import android.content.Context;

import com.tline.android.app.TLineApp;
import com.tline.android.utils.LocaleHelper;
import com.tline.android.utils.PreferencesUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getAppContext();

    TLineApp getApp();

    PreferencesUtils exposePreferencesUtils();

    LocaleHelper exposeLocaleHelper();

}