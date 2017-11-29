package com.tline.android.features.timeline.fragment.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.features.timeline.fragment.interactor.TweetsInteractor;
import com.tline.android.features.timeline.fragment.interactor.impl.TweetsInteractorImpl;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.presenter.impl.TweetsPresenterImpl;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public final class TweetsViewModule {
    @Provides
    public TweetsInteractor provideInteractor(Context context, TwitterApiClient twitterApiClient) {
        return new TweetsInteractorImpl(context, twitterApiClient);
    }

    @Provides
    public PresenterFactory<TweetsPresenter> providePresenterFactory(@NonNull final TweetsInteractor interactor) {
        return new PresenterFactory<TweetsPresenter>() {
            @NonNull
            @Override
            public TweetsPresenter create() {
                return new TweetsPresenterImpl(interactor);
            }
        };
    }


    /**
     * Providing custom API Client for logging and intercepting the requests
     * @return
     */
    @Provides
    public TwitterApiClient provideTwitterApiClient() {
        final TwitterSession activeSession = TwitterCore.getInstance().getSessionManager().getActiveSession();

        // example of custom OkHttpClient with logging HttpLoggingInterceptor
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        final OkHttpClient customClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        // pass custom OkHttpClient into TwitterApiClient and add to TwitterCore
        final TwitterApiClient customApiClient;
        if (activeSession != null) {
            customApiClient = new TwitterApiClient(activeSession, customClient);
            TwitterCore.getInstance().addApiClient(activeSession, customApiClient);
        } else {
            customApiClient = new TwitterApiClient(customClient);
            TwitterCore.getInstance().addGuestApiClient(customApiClient);
        }

        return customApiClient;
    }
}
