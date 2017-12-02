package com.tline.android.features.timeline.fragment.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tline.android.R;
import com.tline.android.utils.DateTimeUtils;
import com.tline.android.utils.DialogUtils;
import com.tline.android.utils.ImageUtils;
import com.twitter.sdk.android.core.models.Tweet;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerViewItemHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    @BindView(R.id.ivTweetImage)
    ImageView mImageViewTweetImage;
    @BindView(R.id.tvTweetText)
    TextView mTextViewTweetWithImage;
    @BindView(R.id.tvUserName)
    TextView mTextViewUserName;
    @BindView(R.id.tvTwitterHandle)
    TextView mTextViewTwitterHandle;
    @BindView(R.id.tvTimeSend)
    TextView mTextViewTimeSend;
    @BindView(R.id.imageView_dp)
    ImageView mImageViewProfileImage;

    Context mContext;
    private Tweet mTweet;

    public RecyclerViewItemHolder(Context context, View view) {
        super(view);

        this.mContext = context;

        ButterKnife.bind(this, view);

        view.setOnClickListener(this);
    }

    public void setTweet(Tweet mTweet) {
        this.mTweet = mTweet;
        bind();
    }

    @Override
    public void onClick(View view) {

        DialogUtils.showTweetDialog(mContext, mTweet);
    }

    private void bind() {

        this.mTextViewUserName.setText(mTweet.user.name);
        this.mTextViewTimeSend.setText(DateTimeUtils.getRelativeTimeAgo(mTweet.createdAt));
        this.mTextViewTwitterHandle.setText(mContext.getString(R.string.prefix_screenname, mTweet.user.screenName));

        this.mTextViewTweetWithImage.setText(mTweet.text);

        ImageUtils.loadImage(mContext, this.mImageViewProfileImage, mTweet.user.profileImageUrl);

        if (!mTweet.entities.media.isEmpty() && mTweet.entities.media.get(0).type.equals("photo")) {
            ImageUtils.loadImage(mContext, this.mImageViewTweetImage, mTweet.entities.media.get(0).mediaUrl);
            mImageViewTweetImage.setVisibility(View.VISIBLE);
        } else {
            mImageViewTweetImage.setVisibility(View.GONE);
        }
    }
}

