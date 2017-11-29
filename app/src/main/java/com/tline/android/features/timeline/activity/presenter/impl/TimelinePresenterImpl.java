package com.tline.android.features.timeline.activity.presenter.impl;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.impl.BasePresenterImpl;
import com.tline.android.features.timeline.activity.presenter.TimelinePresenter;
import com.tline.android.features.timeline.activity.view.TimelineView;
import com.tline.android.features.timeline.activity.interactor.TimelineInteractor;

import javax.inject.Inject;

import timber.log.Timber;

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

        if(viewCreated){
            initTimeline();
        }
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

    @Override
    public void saveSelectedTabIndex(int selectedTabIndex) {
        mInteractor.saveSelectedTabIndex(selectedTabIndex);
    }

    @Override
    public void logout() {
        assert mView != null;
        mView.logoutTwitter();
        mView.launchLoginActivity();
    }

    @Override
    public void switchAppLocale() {
        mInteractor.switchAppLocale();
    }


    private void initTimeline() {

        assert mView != null;
        mView.setSelectedNavItemId(mInteractor.retrieveSelectedTabIndex());
        mView.showInitialFragment();
    }
}