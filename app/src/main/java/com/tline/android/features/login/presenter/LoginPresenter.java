package com.tline.android.features.login.presenter;

import com.tline.android.app.presenter.BasePresenter;
import com.tline.android.features.login.view.LoginView;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

public interface LoginPresenter extends BasePresenter<LoginView> {

    void checkSession(TwitterSession activeSession);

    void handleLoginSuccess(TwitterSession twitterSession);

    void handleLoginFailure(TwitterException twitterException);

    void logout();
}