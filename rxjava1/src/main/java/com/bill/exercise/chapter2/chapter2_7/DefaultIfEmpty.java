package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;
import rx.Subscriber;

/**
 * 判断源Observable是否发送了数据，如果源Observable发送了数据，则正常发送这些数据
 * 否则发送一个默认的数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class DefaultIfEmpty {

    public static void main(String[] args) {
        Observable.create((Subscriber<? super Integer> subscriber) -> {
            subscriber.onCompleted();
        }).defaultIfEmpty(19).subscribe(result -> {
            System.out.println("empty: " + result);
        });
    }

}
