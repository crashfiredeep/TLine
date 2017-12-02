package com.tline.android.features.timeline.fragment.view;

import android.support.annotation.UiThread;

import com.tline.android.app.view.BaseFragmentView;
import com.tline.android.app.view.BaseView;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

@UiThread
public interface TweetsView extends BaseFragmentView {

    String getTwitterHandle();

    void loadData(List<Tweet> tweets);

    void showData();

    void showErrorMessage(String message);

    List<Tweet> getList();
}