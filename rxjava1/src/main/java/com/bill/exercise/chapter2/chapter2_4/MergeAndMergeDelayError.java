package com.bill.exercise.chapter2.chapter2_4;

import rx.Observable;
import rx.Subscriber;

/**
 * merge操作符将多个Observable发送的数据整合起来发送，对外就如同是
 * 一个Observable发送的数据一样，遇到错误则会终止merge动作
 *
 * mergeDelayError遇到错误不会终止，merge动作结束后再分发错误
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class MergeAndMergeDelayError {

    public static void main(String[] args) {
        mergeObserver().subscribe(i -> {
            System.out.println("Merge:" + i);
        });

        mergeDelayErrorObserver().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("mergeDelayError: " + e);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("MergeDelayError: " + integer);
            }
        });
    }

    private static Observable<Integer> mergeObserver(){
        return Observable.merge(Observable.just(1,2,3), Observable.just(4,5,6));
    }

    private static Observable<Integer> mergeDelayErrorObserver(){
        return Observable.mergeDelayError(Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if (i == 3){
                        subscriber.onError(new Exception("error i == 3"));
                    }
                    subscriber.onNext(i);
                }
            }
        }), Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(5 + i);
                }
                subscriber.onCompleted();
            }
        }));
    }

}
