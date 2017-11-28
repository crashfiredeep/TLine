package com.tline.android.features.timeline.view.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.features.login.view.impl.LoginActivity;
import com.tline.android.features.timeline.injection.DaggerTimelineViewComponent;
import com.tline.android.features.timeline.injection.TimelineViewModule;
import com.tline.android.features.timeline.presenter.TimelinePresenter;
import com.tline.android.features.timeline.view.TimelineView;
import com.tline.android.utils.LocaleHelper;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public final class TimelineActivity extends BaseActivity<TimelinePresenter, TimelineView> implements TimelineView {

    @Inject
    PresenterFactory<TimelinePresenter> mPresenterFactory;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                Timber.e("R.id.action_logout");
                logoutTwitter();
                startLoginActivity();
                return true;
            case R.id.action_language:
                LocaleHelper.switchLocale(this);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void prepareBottomNavigation() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_timeline:
                            Timber.e("R.id.action_timeline");
                            break;
                        case R.id.action_olx_egypt:
                            Timber.e("R.id.action_olx_egypt");
                            break;
                        case R.id.action_android_dev:
                            Timber.e("R.id.action_android_dev");
                            break;
                    }
                    return true;
                }
            }
        );
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
