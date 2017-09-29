package com.trackio.mvp.base;

public abstract class BasePresenter<T> {

    protected T view;

    public void onAttach(T view) {
        this.view = view;
    }

    public void onDetach() {
        view = null;
    }
}
