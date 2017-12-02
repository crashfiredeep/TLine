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
    private boolean mIsLoading;

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
        } else {
            mView.showData();
        }
    }

    private void initLoading() {
        if (mInteractor.isNetworkConnected()) {
            fetchData();
        } else {
            if (mView != null) {
                mView.showErrorMessage(mInteractor.getNetworkErrorString());
            }
        }
    }

    private void fetchData() {
        assert mView != null;
        String twitterHandle = mView.getTwitterHandle();
        mInteractor.fetchTweets(twitterHandle, null, this);
    }

    @Override
    public void onStart() {
        mIsLoading = true;
        assert mView != null;
        mView.showLoading();
    }

    @Override
    public void onSuccess(List<Tweet> tweets) {

        if (mView != null) {
            if (tweets.isEmpty()) {
                mView.showErrorMessage(mInteractor.getEmptyListErrorString());
            } else {
                mView.loadData(tweets);
            }
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
        mIsLoading = false;
        if (mView != null) {
            mView.hideLoading();
        }
    }

    /**
     * Retuns status of loading
     *
     * @return mIsLoading
     */
    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void fetchNextPage(Long maxId) {
        Timber.e("nextPage: " + maxId);
        assert mView != null;
        mInteractor.fetchTweets(mView.getTwitterHandle(), maxId, this);
    }
}