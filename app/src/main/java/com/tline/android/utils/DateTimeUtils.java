package com.tline.android.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Naeem(naeemark@gmail.com)
 * On 29/11/2017.
 * For TLine
 */

public class DateTimeUtils {

    static final String TWITTER_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";

    public static String getRelativeTimeAgo(String rawJsonDate) {
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER_FORMAT, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (relativeDate.equals("")) ? rawJsonDate : relativeDate;
    }

    public static String formatTimeForFooter(String createdAt) {
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER_FORMAT, Locale.ENGLISH);
//1:00 AM - 29 Nov 2017
        try {
            long dateMillis = sf.parse(createdAt).getTime();
            return new SimpleDateFormat("dd MMM yy", Locale.ENGLISH).format(dateMillis);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
