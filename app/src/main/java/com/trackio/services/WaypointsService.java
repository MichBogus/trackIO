package com.trackio.services;

import com.trackio.services.scheduler.SchedulerApi;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.ticofab.androidgpxparser.parser.GPXParser;
import io.ticofab.androidgpxparser.parser.domain.Track;
import io.ticofab.androidgpxparser.parser.domain.TrackPoint;

public class WaypointsService implements WaypointsApi {

    private GPXParser gpxParser;
    private SchedulerApi schedulerApi;
    private List<Track> listOfTracks = new ArrayList<>();

    public WaypointsService(GPXParser gpxParser, SchedulerApi schedulerApi, Observable<InputStream> inputStreamObservable) {
        this.gpxParser = gpxParser;
        this.schedulerApi = schedulerApi;

        if (inputStreamObservable == null)
            return;

        schedulerApi.scheduleInitial(inputStreamObservable, inputStreamConsumer(), onFailConsumer(), onCompleteAction(), WaypointsService.this);
    }

    private Consumer<InputStream> inputStreamConsumer() {
        return new Consumer<InputStream>() {
            @Override
            public void accept(InputStream inputStream) throws Exception {
                parseWayPoint(inputStream);
            }
        };
    }

    private Consumer<Throwable> onFailConsumer() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        };
    }

    private Action onCompleteAction() {
        return new Action() {
            @Override
            public void run() throws Exception {
                schedulerApi.dispose(WaypointsService.this);
            }
        };
    }

    @Override
    public List<Track> parseWayPoint(InputStream inputStream) {
        try {
            listOfTracks.add(gpxParser.parse(inputStream).getTracks().get(0));

            inputStream.close();
            return listOfTracks;
        } catch (IOException | XmlPullParserException e) {
            return null;
        }
    }

    @Override
    public List<Track> getTracks() {
        return listOfTracks;
    }

    @Override
    public List<TrackPoint> getTrackPoints(int fromTrackNumber) {
        return listOfTracks.get(fromTrackNumber).getTrackSegments().get(0).getTrackPoints();
    }
}
