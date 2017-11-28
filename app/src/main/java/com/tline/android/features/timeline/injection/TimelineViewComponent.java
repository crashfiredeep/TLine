package com.tline.android.features.timeline.injection;

import com.tline.android.app.injection.ActivityScope;
import com.tline.android.app.injection.AppComponent;
import com.tline.android.features.timeline.view.impl.TimelineActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = TimelineViewModule.class)
public interface TimelineViewComponent {
    void inject(TimelineActivity activity);
}