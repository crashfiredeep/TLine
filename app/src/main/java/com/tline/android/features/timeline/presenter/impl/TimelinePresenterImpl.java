package com.tline.android.features.timeline.presenter.impl;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.impl.BasePresenterImpl;
import com.tline.android.features.timeline.presenter.TimelinePresenter;
import com.tline.android.features.timeline.view.TimelineView;
import com.tline.android.features.timeline.interactor.TimelineInteractor;

import javax.inject.Inject;

public final class TimelinePresenterImpl extends BasePresenterImpl<TimelineView> implements TimelinePresenter {
    /**
     * The interactor
     */
    @NonNull
    private final TimelineInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public TimelinePresenterImpl(@NonNull TimelineInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}