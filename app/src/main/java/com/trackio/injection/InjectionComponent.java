package com.trackio.injection;

import com.trackio.injection.modules.SchedulerModule;
import com.trackio.mvp.MapShowcaseActivity;
import com.trackio.injection.modules.PresenterModule;
import com.trackio.injection.modules.ServicesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ServicesModule.class,
        PresenterModule.class,
        SchedulerModule.class
})
public interface InjectionComponent {
    void inject(MapShowcaseActivity mapActivity);
}