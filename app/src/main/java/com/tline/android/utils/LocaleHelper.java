package com.tline.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.tline.android.features.splash.SplashActivity;
import com.tline.android.features.timeline.view.impl.TimelineActivity;

import java.util.Locale;

/**
 * Created by Naeem(naeemark@gmail.com)
 * On 28/11/2017.
 * For TLine
 */

public class LocaleHelper {


    public static void switchLocale(Activity activity) {
        Resources resources = activity.getApplicationContext().getResources();
        Configuration config = resources.getConfiguration();
        Locale newLocale = getNewLocale(config);
        config.locale = newLocale;
        config.setLayoutDirection(newLocale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        relaunch(activity.getApplicationContext());
    }

    private static Locale getNewLocale(Configuration configuration) {
        if (configuration.locale.getLanguage().equals("ar")){
            return new Locale("en");
        }else {
            return new Locale("ar");
        }
    }

    private static void relaunch(Context context) {
        Intent i = new Intent(context, TimelineActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
