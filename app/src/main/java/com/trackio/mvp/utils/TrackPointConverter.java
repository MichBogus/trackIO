package com.trackio.mvp.utils;

import com.google.android.gms.maps.model.LatLng;
import com.trackio.mvp.model.TrackPointCluster;

import java.util.ArrayList;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.TrackPoint;

public class TrackPointConverter {

    public static List<TrackPointCluster> convertTrackPoint(List<TrackPoint> trackPointList) {
        List<TrackPointCluster> trackPointCluster = new ArrayList<>();
        for (TrackPoint trackPoint : trackPointList) {
            if (!areTrackPointCordsEmpty(trackPoint)) {
                trackPointCluster.add(new TrackPointCluster(new LatLng(trackPoint.getLatitude(), trackPoint.getLongitude()), trackPoint.getName(), trackPoint.getTime().toString()));
            }
        }
        return trackPointCluster;
    }

    private static boolean areTrackPointCordsEmpty(TrackPoint trackPoint) {
        return trackPoint.getLatitude() == 0 || trackPoint.getLongitude() == 0;
    }
}
