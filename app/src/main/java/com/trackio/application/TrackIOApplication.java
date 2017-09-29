package com.trackio.application;

import android.support.multidex.MultiDexApplication;

import com.trackio.injection.DaggerInjectionComponent;
import com.trackio.injection.InjectionComponent;
import com.trackio.injection.modules.PresenterModule;
import com.trackio.injection.modules.SchedulerModule;
import com.trackio.injection.modules.ServicesModule;

public class TrackIOApplication extends MultiDexApplication {

    public InjectionComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        buildInjector();
    }

    private void buildInjector() {
        injector = DaggerInjectionComponent.builder()
                .servicesModule(new ServicesModule(this))
                .presenterModule(new PresenterModule())
                .schedulerModule(new SchedulerModule())
                .build();
    }
}
