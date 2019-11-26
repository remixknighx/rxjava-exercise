package com.bill.exercise.chapter2.chapter2_6;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class DoOperate {

    public static void main(String[] args) {
        // onSubscribe -> onEach -> onNext -> onCompleted -> on unsubscribe
        doOnEachObserver().subscribe(i -> {
            System.out.println("do: " + i);
        });

        doOnErrorObserver().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("subscriber onNext: " + integer);
            }
        });
    }

    private static Observable<Integer> doOnEachObserver(){
        return Observable.just(1,2,3)
                .doOnEach((Notification<? super Integer> notification) -> {
                    System.out.println("doOnEach send " + notification.getValue() + " type: " + notification.getKind());
                })
                .doOnNext((Integer integer) -> {
                    System.out.println("doOnNext send: " + integer);
                })
                .doOnSubscribe(() -> {
                    System.out.println("on subscribe");
                })
                .doOnUnsubscribe(() -> {
                    System.out.println("on unsubscribe");
                })
                .doOnCompleted(() -> {
                    System.out.println("onCompleted");
                });
    }

    private static Observable<Integer> createObserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i <= 5; i++) {
                    if (i <= 3) {
                        subscriber.onNext(i);
                    } else {
                        subscriber.onError(new Throwable("num > 3"));
                    }
                }
            }
        });
    }

    private static Observable<Integer> doOnErrorObserver() {
        return createObserver()
                .doOnEach((Notification<? super Integer> notification) -> {
                    System.out.println("doOnEach send " + notification.getValue() + " type " + notification.getValue());
                })
                .doOnError((Throwable throwable) -> {
                    System.out.println("onError: " + throwable.getMessage());
                })
                .doOnTerminate(() -> {
                    System.out.println("onTerminate");
                })
                .doAfterTerminate(() -> {
                    System.out.println("doAfterTerminate");
                });
    }

}
