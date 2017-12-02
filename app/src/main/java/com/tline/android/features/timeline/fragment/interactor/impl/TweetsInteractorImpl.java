package com.tline.android.features.timeline.fragment.interactor.impl;

import android.content.Context;

import com.tline.android.R;
import com.tline.android.app.interactor.impl.BaseInteractorImpl;
import com.tline.android.features.timeline.fragment.interactor.TweetsInteractor;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.utils.NetworkUtils;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

import static com.tline.android.constants.AppConstants.DEFAULT_PAGE_SIZE;

public final class TweetsInteractorImpl extends BaseInteractorImpl implements TweetsInteractor {

    private final Context mContext;
    private final TwitterApiClient mTwitterApiClient;

    @Inject
    public TweetsInteractorImpl(Context context, TwitterApiClient twitterApiClient) {

        mContext = context;
        mTwitterApiClient = twitterApiClient;
    }


    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetAvailable(mContext);
    }

    @Override
    public void fetchTweets(String twitterHandle, final Long maxId, final OnFetchDataListener listener) {

        listener.onStart();

        final Call<List<Tweet>> listCall = mTwitterApiClient.getStatusesService().userTimeline(null, twitterHandle, DEFAULT_PAGE_SIZE, null, maxId, false, true, false, true);
        listCall.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Tweet> tweets = removeIfDuplicate(maxId, result.data);
                listener.onSuccess(tweets);
                listener.onComplete();
            }

            @Override
            public void failure(TwitterException exception) {
                listener.onFailure(exception.getMessage());
                listener.onComplete();
            }
        });
    }

    @Override
    public String getNetworkErrorString() {
        return mContext.getString(R.string.error_no_network);
    }

    @Override
    public String getEmptyListErrorString() {
        return mContext.getString(R.string.error_no_items_found);
    }

    /**
     * Checks last element of mList and remove duplication
     *
     * @param requestMaxId
     * @param tweets
     */
    private List<Tweet> removeIfDuplicate(Long requestMaxId, List<Tweet> tweets) {
        return (requestMaxId != null && !tweets.isEmpty() && requestMaxId == tweets.get(0).getId()) ? tweets.subList(1, tweets.size()) : tweets;
    }

}