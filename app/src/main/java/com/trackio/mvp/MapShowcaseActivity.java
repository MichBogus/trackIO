package com.trackio.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.MapView;
import com.trackio.R;
import com.trackio.application.TrackIOApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.ticofab.androidgpxparser.parser.domain.Track;

public class MapShowcaseActivity extends AppCompatActivity implements MapShowcaseView {

    @BindView(R.id.map_view)
    MapView mapView;

    @Inject
    MapPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TrackIOApplication) getApplication()).injector.inject(this);
        setContentView(R.layout.activity_main);

        presenter.context = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDetach();
    }

    @Override
    public void setMap(List<Track> trackList) {
        String test = "";
    }
}
