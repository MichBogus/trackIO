package com.trackio.utils;

import com.google.android.gms.maps.model.LatLng;
import com.trackio.mvp.model.TrackPointCluster;
import com.trackio.mvp.utils.TrackPointConverter;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.TrackPoint;

public class TracPointConverterTest {

    DateTime mockOfCurrentTime = new DateTime(100);

    @Test
    public void shouldConvertDataProperly() {
        List<TrackPointCluster> expectedValue = new ArrayList<>();
        expectedValue.add(new TrackPointCluster(new LatLng(20, 20), "test", mockOfCurrentTime.toString()));

        List<TrackPointCluster> afterConverting = TrackPointConverter.convertTrackPoint(dataToBeConverted());

        Assertions.assertThat(afterConverting.get(0).getPosition()).isEqualTo(expectedValue.get(0).getPosition());
        Assertions.assertThat(afterConverting.get(0).getSnippet()).isEqualTo(expectedValue.get(0).getSnippet());
        Assertions.assertThat(afterConverting.get(0).getTitle()).isEqualTo(expectedValue.get(0).getTitle());
    }

    @Test
    public void shouldNotConvertWhenTrackPointHasEmptyCords() {
        int expectedSize = 0;

        List<TrackPointCluster> afterConverting = TrackPointConverter.convertTrackPoint(dataToBeConvertedWithEmptyCords());

        Assertions.assertThat(afterConverting.size()).isEqualTo(expectedSize);
    }

    private List<TrackPoint> dataToBeConverted() {
        TrackPoint trackPoint = (TrackPoint) new TrackPoint.Builder()
                .setName("test")
                .setLatitude(20.0)
                .setLongitude(20.0)
                .setTime(mockOfCurrentTime)
                .build();

        List<TrackPoint> listToBeConverted = new ArrayList<>();
        listToBeConverted.add(trackPoint);

        return listToBeConverted;
    }

    private List<TrackPoint> dataToBeConvertedWithEmptyCords() {
        TrackPoint trackPoint = (TrackPoint) new TrackPoint.Builder()
                .setName("test")
                .setLatitude(0.0)
                .setLongitude(0.0)
                .setTime(mockOfCurrentTime)
                .build();

        List<TrackPoint> listToBeConverted = new ArrayList<>();
        listToBeConverted.add(trackPoint);

        return listToBeConverted;
    }
}
