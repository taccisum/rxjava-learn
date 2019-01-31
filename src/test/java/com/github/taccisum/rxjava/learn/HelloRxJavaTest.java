package com.github.taccisum.rxjava.learn;

import io.reactivex.Flowable;
import org.junit.Test;

/**
 * @author tac
 * @since 30/01/2019
 */
public class HelloRxJavaTest {
    @Test
    public void name() throws Exception {
        Flowable.just("Hello world")
                .subscribe(o -> System.out.println(o + "!"));
    }
}
