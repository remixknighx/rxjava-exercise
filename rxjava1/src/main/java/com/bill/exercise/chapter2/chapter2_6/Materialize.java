package com.bill.exercise.chapter2.chapter2_6;

import rx.Notification;
import rx.Observable;
import rx.functions.Action1;

/**
 * materialize操作符将OnNext, OnError和OnComplete事件都转化为一个Notification对象
 * 并按照原来的顺序发送出来
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Materialize {

    public static void main(String[] args) {
        materializeObserver().subscribe((Notification<Integer> integerNotification) -> {
            System.out.println("materialize: " + integerNotification.getValue() + " type: " + integerNotification.getKind());
        });

        deMaterializeObserver().subscribe((Integer integer) -> {
            System.out.println("deMaterialize: " + integer);
        });
    }

    private static Observable<Notification<Integer>> materializeObserver(){
        return Observable.just(1,2,3).materialize();
    }

    private static Observable<Integer> deMaterializeObserver(){
        return materializeObserver().dematerialize();
    }

}
