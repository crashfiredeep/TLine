package com.tline.android.features.timeline.fragment.interactor;

import com.tline.android.app.interactor.BaseInteractor;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.presenter.impl.TweetsPresenterImpl;

public interface TweetsInteractor extends BaseInteractor {

    boolean isNetworkConnected();

    void fetchTweets(String twitterHandle, TweetsPresenter.OnFetchDataListener listener);

    String getErrorString();
}