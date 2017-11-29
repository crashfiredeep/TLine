package com.tline.android.features.timeline.fragment.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.tline.android.R;
import com.tline.android.utils.DateTimeUtils;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


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

    List<Tweet> mTweets;
    Context mContext;

    public RecyclerViewItemHolder(Context context, View view, List<Tweet> mTweets) {
        super(view);

        this.mTweets = mTweets;
        this.mContext = context;
        // Attach a click listener to the entire row view
        view.setOnClickListener(this);
        ButterKnife.bind(this, view);
    }

    // Handles the row being being clicked
    @Override
    public void onClick(View view) {

        int position = getLayoutPosition(); // gets item position
        Tweet tweet = mTweets.get(position);
        // We can access the data within the views
        Toast.makeText(mContext, "Loading tweet...", Toast.LENGTH_SHORT).show();

//        Intent i = new Intent(mContext, TweetDetailActivity.class);
//        i.putExtra("tweet",tweet);
//        mContext.startActivity(i);

        Timber.i("Tweet:" + tweet.text);

    }

    public void bind(Tweet tweet) {
        this.mTextViewTweetWithImage.setText(tweet.text);

        this.mTextViewUserName.setText(tweet.user.name);
        this.mTextViewTwitterHandle.setText(mContext.getString(R.string.prefix_screenname, tweet.user.screenName));

        if (!TextUtils.isEmpty(tweet.user.profileImageUrl)) {
            Glide.with(mContext).load(tweet.user.profileImageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(this.mImageViewProfileImage);
        }

        this.mTextViewTimeSend.setText(DateTimeUtils.getRelativeTimeAgo(tweet.createdAt));


        if (!tweet.entities.media.isEmpty() && tweet.entities.media.get(0).type.equals("photo")) {
            if (!TextUtils.isEmpty(tweet.text)) {
                Glide.with(mContext).load(tweet.entities.media.get(0).mediaUrl)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(this.mImageViewTweetImage);
                mImageViewTweetImage.setVisibility(View.VISIBLE);
            }
        }
    }
}
