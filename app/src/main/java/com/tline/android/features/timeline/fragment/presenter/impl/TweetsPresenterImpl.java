package com.tline.android.features.timeline.fragment.presenter.impl;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.impl.BasePresenterImpl;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.view.TweetsView;
import com.tline.android.features.timeline.fragment.interactor.TweetsInteractor;

import javax.inject.Inject;

public final class TweetsPresenterImpl extends BasePresenterImpl<TweetsView> implements TweetsPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final TweetsInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public TweetsPresenterImpl(@NonNull TweetsInteractor interactor) {
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