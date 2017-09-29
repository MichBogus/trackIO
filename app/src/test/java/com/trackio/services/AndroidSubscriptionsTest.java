package com.trackio.services;

import com.trackio.services.scheduler.AndroidSubscriptions;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class AndroidSubscriptionsTest {

    AndroidSubscriptions systemUnderTest;

    @Before
    public void setUp() {
        systemUnderTest = new AndroidSubscriptions();
    }

    @Test
    public void shouldReturnProperValueBeforeAddingAnySubscriptions() {
        int expectedSize = 0;

        List<Disposable> subscriptions = systemUnderTest.getSubscriptions(this);

        Assertions.assertThat(subscriptions.size()).isEqualTo(expectedSize);
    }

    @Test
    public void shouldNotDisposeAnySubscriptions() {
        int expectedSize = 0;

        int disposed = systemUnderTest.dispose(this);

        Assertions.assertThat(disposed).isEqualTo(expectedSize);
    }

    @Test
    public void shouldReturnProperValueAfterAddingSubscription() {
        int expectedSize = 1;

        givenSubscriptionsAreNotEmpty();

        List<Disposable> subscriptions = systemUnderTest.getSubscriptions(this);

        Assertions.assertThat(subscriptions.size()).isEqualTo(expectedSize);
    }

    @Test
    public void shouldReturnProperValueAfterDisposingSubscription() {
        int expectedSize = 1;

        givenSubscriptionsAreNotEmpty();

        systemUnderTest.getSubscriptions(this);
        int disposed = systemUnderTest.dispose(this);

        Assertions.assertThat(disposed).isEqualTo(expectedSize);
    }

    private void givenSubscriptionsAreNotEmpty() {
        List<Disposable> disposableList = new ArrayList<>();
        disposableList.add(Single.just("test").subscribe());
        systemUnderTest.subscriptions.put(this.toString(), disposableList);
    }
}
