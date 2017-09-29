package com.trackio.services.scheduler;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AndroidScheduler implements SchedulerApi {

    private SubscriptionsApi subscriptionsApi;

    public AndroidScheduler(SubscriptionsApi subscriptionsApi) {
        this.subscriptionsApi = subscriptionsApi;
    }

    @Override
    public <T> void scheduleInitial(Observable<T> observable, Consumer<T> onNext, Consumer<Throwable> onFail, Action onComplete, Object tag) {
        List<Disposable> subscriptions = subscriptionsApi.getSubscriptions(tag);
        subscriptions.add(observable.subscribe(onNext, onFail, onComplete));
    }

    @Override
    public void dispose(Object tag) {
        subscriptionsApi.dispose(tag);
    }
}
