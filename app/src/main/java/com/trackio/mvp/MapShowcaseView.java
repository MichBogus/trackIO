package com.trackio.mvp;

import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.Track;

public interface MapShowcaseView {
    void setMap(List<Track> trackList);
}
