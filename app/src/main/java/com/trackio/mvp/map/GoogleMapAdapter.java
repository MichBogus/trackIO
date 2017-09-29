package com.trackio.mvp.map;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.ticofab.androidgpxparser.parser.domain.TrackPoint;
import nz.co.trademe.mapme.LatLng;
import nz.co.trademe.mapme.MapMeAdapter;
import nz.co.trademe.mapme.annotations.AnnotationFactory;
import nz.co.trademe.mapme.annotations.MapAnnotation;

public class GoogleMapAdapter extends MapMeAdapter {

    private List<TrackPoint> markers;

    public GoogleMapAdapter(Context context, List<TrackPoint> markers, AnnotationFactory factory) {
        super(context, factory);
        this.markers = markers;
    }

    @NotNull
    @Override
    public MapAnnotation onCreateAnnotation(AnnotationFactory annotationFactory, int position, int annotationType) {
        TrackPoint item = markers.get(position);
        return annotationFactory.createMarker(new LatLng(item.getLatitude(), item.getLongitude()), null, item.getName());
    }

    @Override
    public void onBindAnnotation(MapAnnotation mapAnnotation, int i, Object o) {

    }

    @Override
    public int getItemCount() {
        return markers.size();
    }
}
