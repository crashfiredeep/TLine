package com.tline.android.app.injection;

import android.content.Context;

import com.tline.android.app.TLineApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    TLineApp getApp();
}