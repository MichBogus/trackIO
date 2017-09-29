package com.trackio.services.scheduler;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public interface SchedulerApi {

    <T> void scheduleInitial(Observable<T> observable, Consumer<T> onNext, Consumer<Throwable> onFail, Action onComplete, Object tag);

    void dispose(Object tag);
}
