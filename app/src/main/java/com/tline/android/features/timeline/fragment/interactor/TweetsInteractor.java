package com.tline.android.features.timeline.fragment.interactor;

import com.tline.android.app.interactor.BaseInteractor;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.presenter.impl.TweetsPresenterImpl;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public interface TweetsInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    void fetchTweets(String twitterHandle, OnFetchDataListener listener);

    String getErrorString();


    interface OnFetchDataListener {

        void onStart();

        void onSuccess(List<Tweet> tweets);

        void onFailure(String message);

        void onComplete();
    }
}