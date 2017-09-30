package com.trackio.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.maps.android.clustering.ClusterManager;
import com.trackio.R;
import com.trackio.application.TrackIOApplication;
import com.trackio.mvp.map.GoogleMapAdapter;
import com.trackio.mvp.model.TrackPointCluster;
import com.trackio.mvp.utils.TrackPointConverter;

import java.util.List;

import javax.inject.Inject;

import io.ticofab.androidgpxparser.parser.domain.Track;
import nz.co.trademe.mapme.MapMeAdapter;
import nz.co.trademe.mapme.googlemaps.GoogleMapAnnotationFactory;

public class MapShowcaseActivity extends AppCompatActivity implements MapShowcaseView {

    @Inject
    MapPresenter presenter;

    private MapView mapView;
    private ClusterManager<TrackPointCluster> clusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TrackIOApplication) getApplication()).injector.inject(this);
        setContentView(R.layout.activity_main);

        initViews(savedInstanceState);

        presenter.context = this;
    }

    private void initViews(Bundle savedInstanceState) {
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
        mapView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDetach();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void setMap(final List<TrackPointCluster> trackList) {
        final MapMeAdapter googleMapAdapter = new GoogleMapAdapter(this, trackList, new GoogleMapAnnotationFactory());

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                presenter.clusterMarkersOnTheMap(googleMap, trackList);

                googleMapAdapter.attach(mapView, googleMap);
                googleMapAdapter.notifyDataSetChanged();
            }
        });
    }
}
