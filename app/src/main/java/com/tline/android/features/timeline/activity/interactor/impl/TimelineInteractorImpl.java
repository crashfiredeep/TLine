package com.tline.android.features.timeline.activity.interactor.impl;

import javax.inject.Inject;

import com.tline.android.app.interactor.impl.BaseInteractorImpl;
import com.tline.android.features.timeline.activity.interactor.TimelineInteractor;
import com.tline.android.features.timeline.activity.view.impl.TimelineActivity;
import com.tline.android.utils.LocaleHelper;
import com.tline.android.utils.PreferencesUtils;

public final class TimelineInteractorImpl extends BaseInteractorImpl implements TimelineInteractor {

    private final PreferencesUtils mPreferencesUtils;
    private final LocaleHelper mLocaleHelper;

    @Inject
    public TimelineInteractorImpl(PreferencesUtils preferencesUtils, LocaleHelper localeHelper) {

        mPreferencesUtils = preferencesUtils;
        mLocaleHelper = localeHelper;
    }

    @Override
    public void saveSelectedTabIndex(int selectedTabIndex) {

        mPreferencesUtils.putInt(PreferencesUtils.PrefKeys.SELECTED_TAB_INDEX.name(), selectedTabIndex);
    }

    @Override
    public int retrieveSelectedTabIndex() {
        int index = mPreferencesUtils.getInt(PreferencesUtils.PrefKeys.SELECTED_TAB_INDEX.name());
        return (index <= 0) ? 0 : index;
    }

    @Override
    public void switchAppLocale() {
        mLocaleHelper.switchLocale();
    }
}