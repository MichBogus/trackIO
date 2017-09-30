package com.trackio.services;

import java.io.InputStream;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.Track;
import io.ticofab.androidgpxparser.parser.domain.TrackPoint;

public interface WaypointsApi {

    List<Track> getTracks();

    List<TrackPoint> getTrackPoints(int fromTrackNumber);

    List<Track> parseWayPoint(InputStream inputStream);
}
