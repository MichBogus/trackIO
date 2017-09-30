package com.trackio.mvp.utils;

import com.trackio.mvp.model.TrackPointCluster;

import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.TrackPoint;

public interface TrackPointConverterApi {

    List<TrackPointCluster> convertTrackPoint(List<TrackPoint> trackPointList);
}
