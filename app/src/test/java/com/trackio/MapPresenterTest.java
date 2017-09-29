package com.trackio;

import android.content.Context;

import com.trackio.mvp.MapPresenter;
import com.trackio.mvp.MapShowcaseView;
import com.trackio.services.WaypointsApi;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.Track;
import io.ticofab.androidgpxparser.parser.domain.TrackSegment;

import static com.nhaarman.mockito_kotlin.MockitoKt.whenever;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MapPresenterTest {

    MapPresenter systemUnderTest;

    MapShowcaseView mockOfView = mock(MapShowcaseView.class);
    WaypointsApi mockOfWaypointsApi = mock(WaypointsApi.class);
    Context mockOfContext = mock(Context.class);

    @Before
    public void setUp() throws IOException {

        systemUnderTest = spy(new MapPresenter(mockOfWaypointsApi));

        systemUnderTest.context = mockOfContext;
    }

    @Test
    public void shouldAttachProperly() {
        whenever(mockOfWaypointsApi.getTracks()).thenReturn(thenReturnTrackList());

        systemUnderTest.onAttach(mockOfView);

        verify(mockOfWaypointsApi).getTracks();
        verify(mockOfView).setMap(thenReturnTrackList());
    }

    @Test
    public void shouldDetachProperly() {
        systemUnderTest.onAttach(mockOfView);
        systemUnderTest.onDetach();

        Assertions.assertThat(systemUnderTest.context).isNull();
    }

    private List<Track> thenReturnTrackList() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track.Builder()
                .setTrackName("test")
                .setTrackSegments(ArgumentMatchers.<TrackSegment>anyList()).build());
        return tracks;
    }
}
