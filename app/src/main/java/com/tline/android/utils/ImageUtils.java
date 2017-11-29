package com.tline.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tline.android.R;

/**
 * Created by Naeem(naeemark@gmail.com)
 * On 29/11/2017.
 * For TLine
 */

public class ImageUtils {
    /**
     * Generifies the image loading through the application
     *
     * @param context
     * @param target
     * @param url
     */
    public static void loadImage(Context context, final ImageView target, String url) {

        Glide.with(context)
                .load((url == null || url.isEmpty()) ? R.drawable.ic_launcher_round_web : url)
                .asBitmap()
                .placeholder(R.drawable.ic_launcher_round_web)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        target.setImageBitmap(arg0);
                    }
                });
    }
}
