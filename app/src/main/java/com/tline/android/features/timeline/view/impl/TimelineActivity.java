package com.tline.android.features.timeline.view.impl;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
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
//                Locale locale = new Locale("en");
//                Locale.setDefault(locale);
//
//                Configuration config = getResources().getConfiguration();
//                config.setLocale(locale);
//                createConfigurationContext(config);
//                getResources().updateConfiguration(config, getResources().getDisplayMetrics());


//                ((TextView)findViewById(R.id.textView)).setText(R.string.sample_text);
                changeLocale(this, new Locale("en_US"));
                return true;
            case R.id.action_language_ar:
                Timber.e("R.id.action_language_ar");
                menuItemEn.setVisible(true);
                menuItemAr.setVisible(false);
//                Locale localea = new Locale("ar");
//                Locale.setDefault(localea);
//
//                Configuration configa = getResources().getConfiguration();
//                configa.setLocale(localea);
//                createConfigurationContext(configa);
//                getResources().updateConfiguration(configa, getResources().getDisplayMetrics());
//                ((TextView)findViewById(R.id.textView)).setText(R.string.sample_text);
//                recreate();
//
                changeLocale(this, new Locale("ar"));

                return true;
        }

        if (id == R.id.action_logout) {
            Timber.e("R.id.action_logout");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public static void changeLocale(Context context, Locale locale) {
//        Configuration conf = context.getResources().getConfiguration();
//        conf.locale = locale;
//        Locale.setDefault(locale);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            conf.setLayoutDirection(conf.locale);
//        }
//
//        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());


        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }
}
