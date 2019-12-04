package com.bill.exercise.chapter8;

import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;

/**
 * 指在异步场景下，被观察者发送事件速度远快于观察者处理的速度，从而导致下游的buffer溢出。
 * 背压出现的两个条件：
 * 1. 异步，观察者和被观察者处于不同的线程中
 * 2. 基于push模型
 */
public class BackPressureExercise {

    public static void main(String[] args) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0;; i++) {
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(integer -> {
            System.out.println("integer: " + integer);
        });

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
