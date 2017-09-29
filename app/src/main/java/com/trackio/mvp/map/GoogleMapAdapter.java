package com.trackio.mvp.map;

import android.content.Context;

import com.trackio.mvp.model.TrackPointCluster;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import nz.co.trademe.mapme.LatLng;
import nz.co.trademe.mapme.MapMeAdapter;
import nz.co.trademe.mapme.annotations.AnnotationFactory;
import nz.co.trademe.mapme.annotations.MapAnnotation;

public class GoogleMapAdapter extends MapMeAdapter {

    private List<TrackPointCluster> markers;

    public GoogleMapAdapter(Context context, List<TrackPointCluster> markers, AnnotationFactory factory) {
        super(context, factory);
        this.markers = markers;
    }

    @NotNull
    @Override
    public MapAnnotation onCreateAnnotation(AnnotationFactory annotationFactory, int position, int annotationType) {
        TrackPointCluster item = markers.get(position);
        return annotationFactory.createMarker(new LatLng(item.getPosition().latitude, item.getPosition().longitude), null, item.getTitle());
    }

    @Override
    public void onBindAnnotation(MapAnnotation mapAnnotation, int i, Object o) {

    }

    @Override
    public int getItemCount() {
        return markers.size();
    }
}
