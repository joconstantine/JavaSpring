package com.dailycodebuffer.reactiveprogramming;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

    @Test
    public void testBackPressure() {
        var numbers = Flux.range(1, 100).log();
//        numbers.subscribe(integer -> {
//            System.out.println("integer = " + integer);
//        });

        numbers.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("integer = " + value);
                if (value == 3) cancel();
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed!");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }
        });
    }

    @Test
    public void testBackPressureDrop() {
        var numbers = Flux.range(1, 100).log();

        numbers
                .onBackpressureDrop(integer -> {
                    System.out.println("Dropped Values = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("integer = " + value);
//                if (value == 3) hookOnCancel();
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("Completed!");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }
        });
    }

    @Test
    public void testBackPressureBuffer() {
        var numbers = Flux.range(1, 100).log();

        numbers
                .onBackpressureBuffer(10, integer -> {
                    System.out.println("integer = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("integer = " + value);
//                if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }
                });
    }

    @Test
    public void testBackPressureError() {
        var numbers = Flux.range(1, 100).log();

        numbers
                .onBackpressureError()
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("integer = " + value);
//                if (value == 3) hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("Completed!");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("throwable = " + throwable);
                    }
                });
    }
}
