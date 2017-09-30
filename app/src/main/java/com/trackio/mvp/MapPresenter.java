package com.trackio.mvp;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;
import com.trackio.mvp.base.BasePresenter;
import com.trackio.mvp.model.TrackPointCluster;
import com.trackio.mvp.utils.TrackPointConverterApi;
import com.trackio.services.WaypointsApi;

import java.util.List;

public class MapPresenter extends BasePresenter<MapShowcaseView> {

    public static final int SMALLEST_TRACK = 1;

    public Context context;
    public ClusterManager<TrackPointCluster> clusterManager;

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

    public void clusterMarkersOnTheMap(GoogleMap googleMap, List<TrackPointCluster> trackPointClusterList) {
        clusterManager = new ClusterManager<>(context, googleMap);
        googleMap.setOnCameraIdleListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);
        googleMap.setOnInfoWindowClickListener(clusterManager);
        clusterManager.addItems(trackPointClusterList);
        clusterManager.cluster();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;

        if (clusterManager != null) {
            clusterManager.clearItems();
            clusterManager = null;
        }
    }
}
