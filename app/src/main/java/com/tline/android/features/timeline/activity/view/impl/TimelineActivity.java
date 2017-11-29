package com.tline.android.features.timeline.activity.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.features.login.view.impl.LoginActivity;
import com.tline.android.features.timeline.activity.injection.DaggerTimelineViewComponent;
import com.tline.android.features.timeline.activity.injection.TimelineViewModule;
import com.tline.android.features.timeline.activity.presenter.TimelinePresenter;
import com.tline.android.features.timeline.fragment.view.impl.TweetsFragment;
import com.tline.android.features.timeline.activity.view.TimelineView;
import com.twitter.sdk.android.core.TwitterCore;

import javax.inject.Inject;

import butterknife.BindView;

import static com.tline.android.constants.AppConstants.TWITTER_HANDLE_ANDROID_DEV;
import static com.tline.android.constants.AppConstants.TWITTER_HANDLE_OLX_EGYPT;

public final class TimelineActivity extends BaseActivity<TimelinePresenter, TimelineView> implements TimelineView {

    private static final String SELECTED_NAV_ITEM = "SELECTED_NAV_ITEM";
    @Inject
    PresenterFactory<TimelinePresenter> mPresenterFactory;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    private int mSelectedNavItemId;


    Fragment mUserTimeline = TweetsFragment.newInstance(TwitterCore.getInstance().getSessionManager().getActiveSession().getUserName());
    Fragment mOlxEgypt = TweetsFragment.newInstance(TWITTER_HANDLE_OLX_EGYPT);
    Fragment mAndroidDev = TweetsFragment.newInstance(TWITTER_HANDLE_ANDROID_DEV);

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTimelineViewComponent.builder()
                .appComponent(parentComponent)
                .timelineViewModule(new TimelineViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<TimelinePresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_timeline;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        prepareBottomNavigation();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        assert mPresenter != null;
        switch (id) {
            case R.id.action_logout:
                mPresenter.logout();
                return true;
            case R.id.action_language:
                mPresenter.switchAppLocale(this);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void prepareBottomNavigation() {

        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectFragment(item);
                        return true;
                    }
                }
        );
    }

    private void selectFragment(MenuItem item) {

        assert mPresenter != null;
        switch (item.getItemId()) {
            case R.id.action_timeline:
                mPresenter.saveSelectedTabIndex(0);
                replaceFragment(mUserTimeline);
                break;
            case R.id.action_olx_egypt:
                mPresenter.saveSelectedTabIndex(1);
                replaceFragment(mOlxEgypt);
                break;
            case R.id.action_android_dev:
                mPresenter.saveSelectedTabIndex(2);
                replaceFragment(mAndroidDev);
                break;
        }

    }

    @Override
    public void showInitialFragment() {

        MenuItem selectedItem = mBottomNavigationView.getMenu().getItem(mSelectedNavItemId);
        if (selectedItem == null) {
            selectedItem = mBottomNavigationView.getMenu().getItem(0);
        }
        selectedItem.setChecked(true);
        selectFragment(selectedItem);
    }

    @Override
    public void setSelectedNavItemId(int mSelectedNavItemId) {

        this.mSelectedNavItemId = mSelectedNavItemId;
    }

    @Override
    public void launchLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void replaceFragment(Fragment targetFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, targetFragment, targetFragment.getTag());
        ft.commit();
    }
}
