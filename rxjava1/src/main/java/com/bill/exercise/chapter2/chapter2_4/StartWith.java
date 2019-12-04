package com.bill.exercise.chapter2.chapter2_4;

import rx.Observable;
import rx.Subscriber;

/**
 * startWith操作符会在源Observable发送的数据前面插入一些数据
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class StartWith {

    public static void main(String[] args) {
        new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };
        startWithObserver().subscribe(i -> {
            System.out.println("StartWith:" + i);
        });
    }

    private static Observable<Integer> startWithObserver(){
        return Observable.just(1,2,3).startWith(-1, 0);
    }

}
