package com.trackio.services.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class AndroidSubscriptions implements SubscriptionsApi {

    private Map<String, List<Disposable>> subscriptions = new HashMap();

    private String getSubscriberTag(Object subscription) {
        return subscription.toString();
    }

    @Override
    public List<Disposable> getSubscriptions(Object subscription) {
        String tag = getSubscriberTag(subscription);
        List<Disposable> objectSubscriptions = subscriptions.get(tag);
        if (objectSubscriptions == null) {
            objectSubscriptions = new ArrayList<>();
            subscriptions.put(tag, objectSubscriptions);
        }
        return objectSubscriptions;
    }

    @Override
    public int dispose(Object subscriber) {
        String tag = getSubscriberTag(subscriber);

        if (subscriptions.containsKey(tag)) {
            List<Disposable> taggedSubscriptions = subscriptions.get(tag);

            int numberOfSubscriptions = taggedSubscriptions.size();
            for (Disposable subscription : taggedSubscriptions) {
                subscription.dispose();
            }
            subscriptions.remove(tag);
            return numberOfSubscriptions;
        } else {
            return 0;
        }
    }
}
