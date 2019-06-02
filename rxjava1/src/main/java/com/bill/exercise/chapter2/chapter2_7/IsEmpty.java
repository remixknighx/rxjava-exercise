package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;
import rx.Subscriber;

/**
 * 判断源Observable是否发送过数据，如果发送过就会返回false
 * 如果没发送过，则返回true
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class IsEmpty {

    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onCompleted();
            }
        }).isEmpty().subscribe(result -> {
            System.out.println("isEmpty: " + result);
        });
    }

}
