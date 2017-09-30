package com.trackio.mvp;

import android.content.Context;

import com.trackio.mvp.base.BasePresenter;
import com.trackio.mvp.utils.TrackPointConverterApi;
import com.trackio.services.WaypointsApi;

public class MapPresenter extends BasePresenter<MapShowcaseView> {

    public static final int SMALLEST_TRACK = 1;

    public Context context;

    private WaypointsApi waypointsApi;
    private TrackPointConverterApi trackPointConverterApi;

    public MapPresenter(WaypointsApi waypointsApi, TrackPointConverterApi trackPointConverterApi) {
        this.waypointsApi = waypointsApi;
        this.trackPointConverterApi = trackPointConverterApi;
    }

    @Override
    public void onAttach(MapShowcaseView view) {
        super.onAttach(view);

        view.setMap(trackPointConverterApi.convertTrackPoint(waypointsApi.getTrackPoints(SMALLEST_TRACK)));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
