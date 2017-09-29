package com.trackio.injection;

import com.trackio.MainActivity;
import com.trackio.injection.modules.ServicesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ServicesModule.class})
public interface InjectionComponent {
    void inject(MainActivity mainActivity);
}