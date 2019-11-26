package com.bill.exercise.chapter2.chapter2_4;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * join操作符根据时间窗口来组合两个Observable发送的数据，
 * 每个Observable都有一个自己的时间窗口，在这个时间窗口内的数据都是有效的,
 * join中的每个数据可以组合多次
 *
 * groupjoin和join基本相同，只是通过groupjoin操作符组合后，
 * 发送出来的是一个个小的Observable，每个Observable里面包含了一轮组合数据
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class JoinAndGroupJoin {

    public static void main(String[] args) {
        joinObserver().subscribe(s -> {
            System.out.println("join: " + s);
        });

        groupJoinObserver().subscribe(s -> {
            s.subscribe(str -> {System.out.println("groupjoin: " + str);});
        });
    }

    private static Observable<String> getLeftObservable(){
        return Observable.just("a", "b", "c");
    }

    private static Observable<Long> getRightObservable(){
        return Observable.just(1L, 2L, 3L);
    }

    private static Observable<String> joinObserver() {
        return getLeftObservable().join(getRightObservable(), (str) -> {
            return Observable.timer(1000, TimeUnit.MILLISECONDS);
        }, (aLong) -> {
            return Observable.timer(1000, TimeUnit.MILLISECONDS);
        }, (String left, Long right) -> {
            return left + ":" + right;
        });
    }

    private static Observable<Observable<String>> groupJoinObserver() {
        return getLeftObservable().groupJoin(getRightObservable(),
                (str) -> {
                    return Observable.timer(1000, TimeUnit.MILLISECONDS);
                }, (aLong) -> {
                    return Observable.timer(1000, TimeUnit.MILLISECONDS);
                }, (String left, Observable<Long> longObservable) -> {
                    return longObservable.map(right -> {
                        return left + ":" + right;
                    });
                });
    }

}
