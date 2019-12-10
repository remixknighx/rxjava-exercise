package com.bill.exercise;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * ObserveOn
 * specify the Scheduler on which an observer will observe this Observable
 * 指定一个观察者在哪个调度器上观察这个Observable
 * 每一次调用都起作用
 *
 * SubscribeOn
 * specify the Scheduler on which an Observable will operate
 * 指定Observable自身在哪个调度器上执行
 * 只在最后一次调用起作用
 */
public class SubscribeObserveOn {

    public static void main(String[] args) {
        Observable.just(1)
                .observeOn(Schedulers.newThread())
                .map((@NonNull Integer integer) -> {
                    System.out.println("map-1:" + Thread.currentThread().getName());
                    return integer;
                })
                .observeOn(Schedulers.newThread())
                .map((@NonNull Integer integer) -> {
                    System.out.println("map-2:" + Thread.currentThread().getName());
                    return integer;
                })
                .observeOn(Schedulers.io())
                .map((@NonNull Integer integer) -> {
                    System.out.println("map-3:" + Thread.currentThread().getName());
                    return integer;
                })
                .subscribeOn(Schedulers.single())
                .subscribe((@NonNull Integer integer) -> {
                    System.out.println("subscribe:" + Thread.currentThread().getName());
                });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
