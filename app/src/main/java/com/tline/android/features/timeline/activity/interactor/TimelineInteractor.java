package com.tline.android.features.timeline.activity.interactor;

import android.app.Activity;

import com.tline.android.app.interactor.BaseInteractor;

public interface TimelineInteractor extends BaseInteractor {

    void saveSelectedTabIndex(int selectedTabIndex);

    int retrieveSelectedTabIndex();

    void switchAppLocale(Activity activity);
}