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
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.app.view.impl.BaseFragment;
import com.tline.android.constants.AppConstants;
import com.tline.android.features.timeline.fragment.injection.DaggerTweetsViewComponent;
import com.tline.android.features.timeline.fragment.injection.TweetsViewModule;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.view.TweetsView;
import com.tline.android.features.timeline.fragment.view.adapter.RecyclerViewAdapter;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class TweetsFragment extends BaseFragment<TweetsPresenter, TweetsView> implements TweetsView {
    private static final String TARGET_HANDLE = "TARGET_HANDLE";
    private static final String KEY_EXTRA_LIST = "KEY_EXTRA_LIST";
    @Inject
    PresenterFactory<TweetsPresenter> mPresenterFactory;

    protected RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    private String mTwitterHandle;
    private List<Tweet> mList = new ArrayList<>();

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

    private void init() {

        // sets layout manager
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // creates and sets adapter
        mRecyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        // For Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }


    /**
     * includes utility functions required for pagination
     */
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            assert mPresenter != null;
            if (!mPresenter.isLoading()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= AppConstants.DEFAULT_PAGE_SIZE) {
                    loadNextPage();
                }
            }
        }

        private void loadNextPage() {
            assert mPresenter != null;
            mPresenter.fetchNextPage(mList.get(mList.size() - 1).id);
        }
    };

    /**
     * Getter
     *
     * @return mTwitterHandle
     */
    @Override
    public String getTwitterHandle() {
        return mTwitterHandle;
    }

    /**
     * Getter
     *
     * @return mList
     */
    @Override
    public List<Tweet> getList() {
        return mList;
    }

    /**
     * Called by the presenter
     *
     * @param tweets
     */
    @Override
    public void loadData(List<Tweet> tweets) {
        mList.addAll(tweets);

        showData();
    }

    /**
     * Removes old data and adds new list into adapter
     */
    @Override
    public void showData() {
        mRecyclerViewAdapter.clear();
        mRecyclerViewAdapter.addAll(mList);
    }

    @Override
    public void showErrorMessage(String message) {
        ((BaseActivity) getActivity()).showErrorWithMessage(message);
    }

    /**
     * Overriding to support pagination bottom loader
     */
    @Override
    public void showLoading() {
        if (mList.size() == 0) {
            super.showLoading();
        } else {
            getActivity().findViewById(R.id.linearLayout_loader).setVisibility(View.VISIBLE);
        }
    }

    /**
     * Overriding to support pagination bottom loader
     */
    @Override
    public void hideLoading() {
        getActivity().findViewById(R.id.linearLayout_loader).setVisibility(View.GONE);
        super.hideLoading();
    }
}
