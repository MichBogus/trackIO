package com.trackio.services.scheduler;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface SubscriptionsApi {

    List<Disposable> getSubscriptions(Object subscription);

    int dispose(Object subscriber);
}
