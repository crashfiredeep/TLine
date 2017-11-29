package com.tline.android.features.timeline.activity.injection;

import android.support.annotation.NonNull;

import com.tline.android.app.presenter.loader.PresenterFactory;
import com.tline.android.features.timeline.activity.interactor.TimelineInteractor;
import com.tline.android.features.timeline.activity.interactor.impl.TimelineInteractorImpl;
import com.tline.android.features.timeline.activity.presenter.TimelinePresenter;
import com.tline.android.features.timeline.activity.presenter.impl.TimelinePresenterImpl;
import com.tline.android.utils.LocaleHelper;
import com.tline.android.utils.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

@Module
public final class TimelineViewModule {
    @Provides
    public TimelineInteractor provideInteractor(PreferencesUtils preferencesUtils, LocaleHelper localeHelper) {
        return new TimelineInteractorImpl(preferencesUtils, localeHelper);
    }

    @Provides
    public PresenterFactory<TimelinePresenter> providePresenterFactory(@NonNull final TimelineInteractor interactor) {
        return new PresenterFactory<TimelinePresenter>() {
            @NonNull
            @Override
            public TimelinePresenter create() {
                return new TimelinePresenterImpl(interactor);
            }
        };
    }
}
