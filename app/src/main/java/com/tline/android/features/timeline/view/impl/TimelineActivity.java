package com.tline.android.features.timeline.view.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.Locale;

import javax.inject.Inject;

import timber.log.Timber;

public final class TimelineActivity extends BaseActivity<TimelinePresenter, TimelineView> implements TimelineView {

    @Inject
    PresenterFactory<TimelinePresenter> mPresenterFactory;

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

        setActionBarIcon(R.mipmap.ic_launcher);
    }


    MenuItem menuItemEn;
    MenuItem menuItemAr;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

         menuItemEn = menu.findItem(R.id.action_language_en);
         menuItemAr = menu.findItem(R.id.action_language_ar);



        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (getResources().getConfiguration().locale.getLanguage().equals("ar")){
            menu.findItem(R.id.action_language_ar).setVisible(false);
            menu.findItem(R.id.action_language_en).setVisible(true);


        }else{
            menu.findItem(R.id.action_language_ar).setVisible(true);
            menu.findItem(R.id.action_language_en).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_logout:
                Timber.e("R.id.action_logout");
                logoutTwitter();
                startLoginActivity();
                return true;
            case R.id.action_language_en:
                Timber.e("R.id.action_language_en");
                menuItemEn.setVisible(false);
                menuItemAr.setVisible(true);
                changeLocale(this, new Locale("en"));
                return true;
            case R.id.action_language_ar:
                Timber.e("R.id.action_language_ar");
                menuItemEn.setVisible(true);
                menuItemAr.setVisible(false);
                changeLocale(this, new Locale("ar"));

                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void changeLocale(Activity activity, Locale newLocale) {

        Resources resources = activity.getApplicationContext().getResources();
        Configuration config = resources.getConfiguration();
        config.locale = newLocale;
        config.setLayoutDirection(newLocale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        activity.recreate();
    }

    public void switchDirection(Context context) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = getLocale(config);
        config.locale = locale;
        config.setLayoutDirection(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate();
    }

    private Locale getLocale(Configuration configuration) {
        if (configuration.locale.getLanguage().equals("ar")){
            return new Locale("en");
        }else {

            return new Locale("ar");
        }
    }
}
