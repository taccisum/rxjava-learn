package com.github.taccisum.rxjava.learn;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author tac
 * @since 30/01/2019
 */
public class HelloRxJavaTest {
    @Test
    public void name() throws Exception {
        Flowable.just("Hello world")
                .subscribe(o -> System.out.println(o + "!"));

        Observable.just(1L, 2L, "hello").subscribe(o -> {
            System.out.println(Thread.currentThread());
            System.out.println(o.getClass());
            System.out.println(o);
        });
        Observable.fromCallable(() -> Arrays.asList(1L, 2L)).subscribe(System.out::println);
        Observable.fromArray(1L, 2L, 3L).subscribe(System.out::println);

        Observable.create(aSubscriber -> {
            for (int i = 0; i < 5; i++) {
                if (!aSubscriber.isDisposed() && i % 2 == 0) {
                    aSubscriber.onNext("value_" + i);
                }
            }

            if (!aSubscriber.isDisposed()) {
                aSubscriber.onComplete();
            }

            for (int i = 0; i < 5; i++) {
                if (!aSubscriber.isDisposed() && i % 2 == 0) {
                    aSubscriber.onNext("value_" + i);
                }
            }

            // how to use onError()
//            aSubscriber.onError(new RuntimeException());

        })
                .doOnSubscribe(o -> System.out.println("subscribed"))
                .doOnComplete(() -> System.out.println("completed"))
                .subscribe(System.out::println);

        // async
        Observable.create(aSubscriber -> {
            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    if (!aSubscriber.isDisposed()) {
                        aSubscriber.onNext("value_" + i);
                    }
                }

                if (!aSubscriber.isDisposed()) {
                    aSubscriber.onComplete();
                }
            }).start();

        }).subscribe(System.out::println);

        System.out.println("hhh");
    }
}
