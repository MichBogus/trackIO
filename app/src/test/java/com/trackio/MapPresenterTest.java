package com.trackio;

import android.content.Context;

import com.trackio.mvp.MapPresenter;
import com.trackio.mvp.MapShowcaseView;
import com.trackio.mvp.model.TrackPointCluster;
import com.trackio.mvp.utils.TrackPointConverterApi;
import com.trackio.services.WaypointsApi;

import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.TrackPoint;

import static com.nhaarman.mockito_kotlin.MockitoKt.whenever;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MapPresenterTest {

    MapPresenter systemUnderTest;

    MapShowcaseView mockOfView = mock(MapShowcaseView.class);
    WaypointsApi mockOfWaypointsApi = mock(WaypointsApi.class);
    TrackPointConverterApi mockOfTrackPointConverter = mock(TrackPointConverterApi.class);
    Context mockOfContext = mock(Context.class);

    @Before
    public void setUp() {
        systemUnderTest = spy(new MapPresenter(mockOfWaypointsApi, mockOfTrackPointConverter));

        systemUnderTest.context = mockOfContext;
    }

    @Test
    public void shouldAttachProperly() {
        List<TrackPoint> expectedValue = thenReturnTrackPointList();
        whenever(mockOfWaypointsApi.getTrackPoints(MapPresenter.SMALLEST_TRACK)).thenReturn(expectedValue);

        systemUnderTest.onAttach(mockOfView);

        verify(mockOfWaypointsApi).getTrackPoints(MapPresenter.SMALLEST_TRACK);
        verify(mockOfTrackPointConverter).convertTrackPoint(expectedValue);
        verify(mockOfView).setMap(ArgumentMatchers.<TrackPointCluster>anyList());
    }

    @Test
    public void shouldDetachProperly() {
        systemUnderTest.onAttach(mockOfView);
        systemUnderTest.onDetach();

        Assertions.assertThat(systemUnderTest.context).isNull();
        Assertions.assertThat(systemUnderTest.clusterManager).isNull();
    }

    private List<TrackPoint> thenReturnTrackPointList() {
        TrackPoint trackPoint = (TrackPoint) new TrackPoint.Builder()
                .setName("test")
                .setLatitude(0.0)
                .setLongitude(0.0)
                .setTime(new DateTime(100))
                .build();

        List<TrackPoint> listToBeConverted = new ArrayList<>();
        listToBeConverted.add(trackPoint);

        return listToBeConverted;
    }
}
