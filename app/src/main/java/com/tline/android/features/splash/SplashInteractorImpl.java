package com.tline.android.features.splash;

import android.content.Context;

import com.tline.android.R;
import com.tline.android.app.interactor.impl.BaseInteractorImpl;
import com.tline.android.constants.AppConstants;
import com.tline.android.utils.LocaleHelper;
import com.tline.android.utils.NetworkUtils;
import com.tline.android.utils.PreferencesUtils;

import java.util.Locale;

import javax.inject.Inject;

public final class SplashInteractorImpl extends BaseInteractorImpl implements SplashInteractor {

    private final Context mContext;

    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public SplashInteractorImpl(Context context, PreferencesUtils preferencesUtils) {
        this.mContext = context;
        this.mPreferencesUtils = preferencesUtils;
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public boolean isSplashDone() {
        return mPreferencesUtils.getBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name());
    }

    @Override
    public void setSpalshDone() {
        mPreferencesUtils.putBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name(), true);
    }

}