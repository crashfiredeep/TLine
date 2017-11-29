package com.tline.android.features.timeline.activity.presenter;

import android.app.Activity;

import com.tline.android.app.presenter.BasePresenter;
import com.tline.android.features.timeline.activity.view.TimelineView;

public interface TimelinePresenter extends BasePresenter<TimelineView> {

    void saveSelectedTabIndex(int selectedTabIndex);

    void logout();

    void switchAppLocale(Activity activity);
}