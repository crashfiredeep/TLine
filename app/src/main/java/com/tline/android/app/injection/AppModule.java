package com.tline.android.app.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tline.android.app.TLineApp;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final TLineApp mApp;

    public AppModule(@NonNull TLineApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public TLineApp provideApp() {
        return mApp;
    }
}
