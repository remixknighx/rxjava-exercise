package com.bill.exercise.chapter2.chapter2_6;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

/**
 * timeout操作符给Observable加上超时时间，每发送一个数据后就重置计时器，
 * 当超过预定的时间还没有发送下一个数据时，就抛出一个超时的异常
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Timeout {

    public static void main(String[] args) {
        timeoutObserver().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
            }
        });

        timeoutObserverNext().subscribe(integer -> {
            System.out.println("integer " + integer);
        });
    }

    private static Observable<Integer> timeoutObserver(){
        return createObserver().timeout(200, TimeUnit.MILLISECONDS);
    }

    private static Observable<Integer> timeoutObserverNext(){
        return createObserver().timeout(200, TimeUnit.MILLISECONDS, Observable.just(5,6));
    }

    private static Observable<Integer> createObserver(){
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i <= 3; i++) {
                    try {
                        Thread.sleep(i * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });
    }

}
