package com.tline.android.features.timeline.fragment.presenter.impl;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.impl.BasePresenterImpl;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.view.TweetsView;
import com.tline.android.features.timeline.fragment.interactor.TweetsInteractor;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class TweetsPresenterImpl extends BasePresenterImpl<TweetsView> implements TweetsPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final TweetsInteractor mInteractor;

    @Inject
    public TweetsPresenterImpl(@NonNull TweetsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated) {
            loadData();
        }
    }

    private void loadData() {
        assert mView != null;
        if (mView.getList().isEmpty()) {
            initLoading();
        }else{
            mView.showData();
        }
    }

    private void initLoading() {
        if(mInteractor.isNetworkConnected()) {
            fetchData();
        }else{
            if (mView != null) {
                mView.showErrorMessage(mInteractor.getErrorString());
            }
        }
    }

    private void fetchData() {
        assert mView != null;
        String twitterHandle = mView.getTwitterHandle();
        mInteractor.fetchTweets(twitterHandle, this);
    }

    @Override
    public void onStart() {
        assert mView != null;
        mView.showLoading();
    }

    @Override
    public void onSuccess(List<Tweet> tweets) {

        if (mView != null) {
            mView.loadData(tweets);
        }
    }

    @Override
    public void onFailure(String message) {
        if (mView != null) {
            mView.showErrorMessage(message);
        }
    }

    @Override
    public void onComplete() {
        if (mView != null) {
            mView.hideLoading();
        }
    }

}