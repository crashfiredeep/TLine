package com.tline.android.features.timeline.fragment.injection;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.features.timeline.fragment.interactor.TweetsInteractor;
import com.tline.android.features.timeline.fragment.interactor.impl.TweetsInteractorImpl;
import com.tline.android.features.timeline.fragment.presenter.TweetsPresenter;
import com.tline.android.features.timeline.fragment.presenter.impl.TweetsPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class TweetsViewModule {
    @Provides
    public TweetsInteractor provideInteractor() {
        return new TweetsInteractorImpl();
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
}
