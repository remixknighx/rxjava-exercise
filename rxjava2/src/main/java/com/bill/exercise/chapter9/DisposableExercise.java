package com.bill.exercise.chapter9;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * RxJava2中的Subscription，用于订阅和取消订阅
 */
public class DisposableExercise {

    public static void main(String[] args) {
        Disposable disposable = Observable.just("hll").subscribe(s -> {
            System.out.println(s);
        });
        disposable.dispose();
    }

}
