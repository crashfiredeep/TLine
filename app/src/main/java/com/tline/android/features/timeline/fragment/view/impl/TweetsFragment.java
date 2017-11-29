package com.tline.android.features.timeline.fragment.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.app.view.impl.BaseFragment;
import com.tline.android.features.timeline.fragment.injection.DaggerTweetsViewComponent;
import com.tline.android.features.timeline.fragment.injection.TweetsViewModule;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.view.TweetsView;
import com.tline.android.features.timeline.activity.view.impl.TimelineActivity;
import com.tline.android.features.timeline.fragment.view.adapter.RecyclerViewAdapter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import timber.log.Timber;

public final class TweetsFragment extends BaseFragment<TweetsPresenter, TweetsView> implements TweetsView {
    private static final String TARGET_HANDLE = "TARGET_HANDLE";
    @Inject
    PresenterFactory<TweetsPresenter> mPresenterFactory;

    protected RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;

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

        mRecyclerView = view.findViewById(R.id.recyclerView);

        init();

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

    private void init() {

        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public String getTwitterHandle() {
        return mTwitterHandle;
    }

    @Override
    public void loadData(List<Tweet> tweets) {
        mRecyclerViewAdapter.clear();
        mRecyclerViewAdapter.addAll(tweets);
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity)getActivity()).showErrorWithMessage(message);
    }


}
