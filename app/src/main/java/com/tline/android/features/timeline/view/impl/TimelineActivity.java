package com.tline.android.features.timeline.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.features.timeline.injection.DaggerTimelineViewComponent;
import com.tline.android.features.timeline.injection.TimelineViewModule;
import com.tline.android.features.timeline.presenter.TimelinePresenter;
import com.tline.android.features.timeline.view.TimelineView;

import javax.inject.Inject;

public final class TimelineActivity extends BaseActivity<TimelinePresenter, TimelineView> implements TimelineView {

    @Inject
    PresenterFactory<TimelinePresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTimelineViewComponent.builder()
                .appComponent(parentComponent)
                .timelineViewModule(new TimelineViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<TimelinePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_timeline;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

    }
}
