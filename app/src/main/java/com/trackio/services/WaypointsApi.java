package com.trackio.services;

import java.io.InputStream;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.Track;

public interface WaypointsApi {

    List<Track> getTracks();

    List<Track> parseWayPoint(InputStream inputStream);
}
