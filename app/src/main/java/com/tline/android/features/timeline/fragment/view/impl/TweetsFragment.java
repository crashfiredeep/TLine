package com.tline.android.features.timeline.fragment.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseFragment;
import com.tline.android.features.timeline.fragment.injection.DaggerTweetsViewComponent;
import com.tline.android.features.timeline.fragment.injection.TweetsViewModule;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.view.TweetsView;
import com.tline.android.features.timeline.activity.view.impl.TimelineActivity;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import javax.inject.Inject;

public final class TweetsFragment extends BaseFragment<TweetsPresenter, TweetsView> implements TweetsView {
    private static final String TARGET_HANDLE = "TARGET_HANDLE";
    @Inject
    PresenterFactory<TweetsPresenter> mPresenterFactory;
    private String mTwitterHandle;

    public TweetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tweets, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieve text and color from bundle or savedInstanceState
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            mTwitterHandle = args.getString(TARGET_HANDLE);
        } else {
            mTwitterHandle = savedInstanceState.getString(TARGET_HANDLE);
        }

        ((TimelineActivity)getActivity()).showToast("Handle: "+ mTwitterHandle);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        UserTimeline userTimeline = new UserTimeline.Builder().screenName(mTwitterHandle).maxItemsPerRequest(50).build();


        final TweetTimelineRecyclerViewAdapter adapter =
                new TweetTimelineRecyclerViewAdapter.Builder(getActivity())
                        .setTimeline(userTimeline)
                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                        .build();

        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTweetsViewComponent.builder()
                .appComponent(parentComponent)
                .tweetsViewModule(new TweetsViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<TweetsPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    public static Fragment newInstance(String twitterHandle) {
        Fragment fragment = new TweetsFragment();
        Bundle args = new Bundle();
        args.putString(TARGET_HANDLE, twitterHandle);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TARGET_HANDLE, mTwitterHandle);
        super.onSaveInstanceState(outState);
    }
}
