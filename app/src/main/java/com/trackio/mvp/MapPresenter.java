package com.trackio.mvp;

import android.content.Context;

import com.trackio.mvp.base.BasePresenter;
import com.trackio.services.WaypointsApi;

public class MapPresenter extends BasePresenter<MapShowcaseView> {

    public Context context;

    private WaypointsApi waypointsApi;

    public MapPresenter(WaypointsApi waypointsApi) {
        this.waypointsApi = waypointsApi;
    }

    @Override
    public void onAttach(MapShowcaseView view) {
        super.onAttach(view);

        view.setMap(waypointsApi.getTracks());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }
}
