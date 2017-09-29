package com.trackio.injection.modules;

import com.trackio.application.TrackIOApplication;
import com.trackio.services.WaypointsApi;
import com.trackio.services.WaypointsService;
import com.trackio.services.scheduler.SchedulerApi;

import java.io.InputStream;

import dagger.Module;
import dagger.Provides;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.subjects.PublishSubject;
import io.ticofab.androidgpxparser.parser.GPXParser;

@Module
public class ServicesModule {

    private TrackIOApplication application;

    public ServicesModule(TrackIOApplication application) {
        this.application = application;
    }

    @Provides
    public GPXParser provideGPXParser() {
        return new GPXParser();
    }

    @Provides
    public WaypointsApi provideWaypointsService(GPXParser gpxParser, SchedulerApi schedulerApi) {
        return new WaypointsService(gpxParser, schedulerApi, PublishSubject.create(new ObservableOnSubscribe<InputStream>() {
            @Override
            public void subscribe(ObservableEmitter<InputStream> e) throws Exception {
                e.onNext(application.getAssets().open("Track 110-03-01 05-06.gpx"));
                e.onNext(application.getAssets().open("Track 110-03-01 02-56.gpx"));
                e.onNext(application.getAssets().open("Track 110-02-31 23-46 Bardeler Weg.gpx"));
                e.onComplete();
            }
        }));
    }
}