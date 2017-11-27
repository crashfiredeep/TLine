package com.tline.android.features.login.presenter.impl;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.impl.BasePresenterImpl;
import com.tline.android.features.login.presenter.LoginPresenter;
import com.tline.android.features.login.view.LoginView;
import com.tline.android.features.login.interactor.LoginInteractor;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import javax.inject.Inject;

public final class LoginPresenterImpl extends BasePresenterImpl<LoginView> implements LoginPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final LoginInteractor mInteractor;


    @Inject
    public LoginPresenterImpl(@NonNull LoginInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if (viewCreated){
            assert mView != null;
            mView.updateUi();
        }
    }

    @Override
    public void onStop() {
        mInteractor.cancelOnGoingHttpRequest();
        super.onStop();
    }

    @Override
    public void handleLoginSuccess(TwitterSession twitterSession) {
        assert mView != null;
        mView.launchHomeActivity();
    }

    @Override
    public void handleLoginFailure(TwitterException twitterException) {
        assert mView != null;
        mView.showLoginError(twitterException.getMessage());
    }

    @Override
    public void logout() {
        assert mView != null;
        mView.logoutTwitter();
        mView.updateUi();
    }
}