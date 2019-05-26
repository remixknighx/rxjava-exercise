package com.bill.exercise.chapter2.chapter2_5;

import rx.Observable;
import rx.Subscriber;

/**
 * retry操作符在发生错误时重新进行订阅，可以重复多次，所以发送的数据可能重复。
 * 可以指定最大重复次数
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class Retry {

    public static void main(String[] args) {
        retryObserver().subscribe(new Subscriber<Integer>() {
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
    }

    private static Observable<Integer> createObserver(){
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("subscribe");
                for (int i = 0; i < 3; i++) {
                    if (i == 2) {
                        subscriber.onError(new Exception("exception"));
                    } else {
                        subscriber.onNext(i);
                    }
                }
            }
        });
    }

    private static Observable<Integer> retryObserver(){
        return createObserver().retry(2L);
    }

}
