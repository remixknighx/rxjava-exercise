package com.bill.exercise.chapter2.chapter2_4;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

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
            System.out.println("groupjoin: " + s);
        });
    }

    private static Observable<String> getLeftObservable(){
        return Observable.just("a", "b", "c");
    }

    private static Observable<Long> getRightObservable(){
        return Observable.just(1L, 2L, 3L);
    }

    private static Observable<String> joinObserver(){
        return getLeftObservable().join(getRightObservable(), new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                }, new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long s) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                }, new Func2<String, Long, String>() {
                    @Override
                    public String call(String left, Long right) {
                        return left + ":" + right;
                    }
        });
    }

    private static Observable<Observable<String>> groupJoinObserver(){
        return getLeftObservable().groupJoin(getRightObservable(),
                new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                }, new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long s) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                }, new Func2<String, Observable<Long>, Observable<String>>() {
                    @Override
                    public Observable<String> call(String left, Observable<Long> longObservable) {
                        return longObservable.map(right -> {return left + ":" + right;});
                    }
                });
    }

}
