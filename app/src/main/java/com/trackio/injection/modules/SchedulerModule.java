package com.trackio.injection.modules;

import com.trackio.services.scheduler.AndroidScheduler;
import com.trackio.services.scheduler.AndroidSubscriptions;
import com.trackio.services.scheduler.SchedulerApi;
import com.trackio.services.scheduler.SubscriptionsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Provides
    @Singleton
    public SubscriptionsApi provideSubscriptionsApi() {
        return new AndroidSubscriptions();
    }

    @Provides
    public SchedulerApi provideSchedulerApi(SubscriptionsApi subscriptionsApi) {
        return new AndroidScheduler(subscriptionsApi);
    }
}
