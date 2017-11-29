package com.tline.android.features.login.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tline.android.R;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.app.view.impl.BaseActivity;
import com.tline.android.features.login.injection.DaggerLoginViewComponent;
import com.tline.android.features.login.injection.LoginViewModule;
import com.tline.android.features.login.presenter.LoginPresenter;
import com.tline.android.features.login.view.LoginView;
import com.tline.android.features.timeline.activity.view.impl.TimelineActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Call;
import timber.log.Timber;

public final class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {

    @Inject
    PresenterFactory<LoginPresenter> mPresenterFactory;

    @BindView(R.id.imageView)
    protected ImageView mUserDpImageView;

    @BindView(R.id.textView_userName)
    protected TextView mUserNameTextView;

    @BindView(R.id.button_login)
    protected TwitterLoginButton mLoginButton;

    @BindView(R.id.button_logout)
    protected Button mLogoutButton;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerLoginViewComponent.builder()
                .appComponent(parentComponent)
                .loginViewModule(new LoginViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<LoginPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        setButtonListeners();

        if (TwitterCore.getInstance().getSessionManager().getActiveSession() != null) {
            launchHomeActivity();
        }
    }

    private void setButtonListeners() {

        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Timber.i(result.data.getUserName());
                launchHomeActivity();
            }

            @Override
            public void failure(TwitterException exception) {
                showLoginError(exception.getMessage());
            }

        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutTwitter();
                updateUi();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        mLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void updateUi() {

        TwitterSession activeSession = TwitterCore.getInstance().getSessionManager().getActiveSession();

        if (activeSession == null) {
            mLogoutButton.setVisibility(View.GONE);
            mLoginButton.setVisibility(View.VISIBLE);
            mUserDpImageView.setImageResource(R.drawable.ic_launcher_round_web);
        } else {

            mUserNameTextView.setText(getString(R.string.prompt_welcome_prefix, activeSession.getUserName()));
            //ImageUtils.loadImage(this, mUserDpImageView, appUser.getPhotoUrl());
            mLogoutButton.setVisibility(View.VISIBLE);
            mLoginButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void launchHomeActivity() {
        Intent intent = new Intent(this, TimelineActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginError(String message) {
        showErrorWithMessage(message);
    }

}
