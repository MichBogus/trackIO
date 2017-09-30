package com.trackio.mvp;

import com.trackio.mvp.model.TrackPointCluster;

import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.Track;

public interface MapShowcaseView {
    void setMap(List<TrackPointCluster> trackList);
}
