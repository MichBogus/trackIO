package com.trackio.application;

import android.support.multidex.MultiDexApplication;

import com.trackio.injection.DaggerInjectionComponent;
import com.trackio.injection.InjectionComponent;
import com.trackio.injection.modules.ServicesModule;

public class TrackIOApplication extends MultiDexApplication {

    InjectionComponent injectionComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void buildInjector() {
        injectionComponent = DaggerInjectionComponent.builder()
                .servicesModule(new ServicesModule())
                .build();
    }
}
