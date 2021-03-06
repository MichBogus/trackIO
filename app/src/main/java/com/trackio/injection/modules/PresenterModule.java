package com.trackio.injection.modules;

import com.trackio.mvp.MapPresenter;
import com.trackio.mvp.utils.TrackPointConverterApi;
import com.trackio.services.WaypointsApi;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public MapPresenter provideMapPresenter(WaypointsApi waypointsApi, TrackPointConverterApi trackPointConverter) {
        return new MapPresenter(waypointsApi,trackPointConverter);
    }
}
