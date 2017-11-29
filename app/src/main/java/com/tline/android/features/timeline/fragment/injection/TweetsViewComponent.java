package com.tline.android.features.timeline.fragment.injection;

import com.tline.android.app.injection.AppComponent;
import com.tline.android.app.injection.FragmentScope;
import com.tline.android.features.timeline.fragment.view.impl.TweetsFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = TweetsViewModule.class)
public interface TweetsViewComponent {
    void inject(TweetsFragment fragment);
}