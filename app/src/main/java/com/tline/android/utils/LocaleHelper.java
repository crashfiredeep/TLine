package com.tline.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.tline.android.features.timeline.activity.view.impl.TimelineActivity;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Naeem(naeemark@gmail.com)
 * On 28/11/2017.
 * For TLine
 */

@Singleton
public class LocaleHelper {

    private final Context mContext;

    @Inject
    public LocaleHelper(Context context) {
        mContext = context;
    }

    public void switchLocale() {
        Resources resources = mContext.getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = getNewLocale(config);
        config.locale = locale;
        config.setLayoutDirection(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        relaunchTimelineActivity();
    }

    private Locale getNewLocale(Configuration configuration) {
        if (configuration.locale.getLanguage().equals("ar")) {
            return new Locale("en");
        } else {
            return new Locale("ar");
        }
    }

    private void relaunchTimelineActivity() {
        Intent i = new Intent(mContext, TimelineActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }
}
