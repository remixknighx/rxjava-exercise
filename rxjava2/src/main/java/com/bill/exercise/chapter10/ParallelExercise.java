package com.bill.exercise.chapter10;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava的并行编程
 */
public class ParallelExercise {

    public static void main(String[] args) {
        parallelFlowable();
    }

    /**
     * 通过flatMap操作符实现类似于Java8的并行执行效果
     */
    private static void flatMapParallel(){
        Observable.range(1, 500).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(integer).map((integer2) -> {
                    return integer2.toString();
                });
            }
        }).observeOn(Schedulers.computation())
                .subscribe((str) -> {
            System.out.println(Thread.currentThread().getName() + "~" + str);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ParallelFlowable是并行的Flowable版本
     * 支持以下操作符：
     * map, filter, flatMap, concatMap，reduce, collect, sorted, toSortedList, compose
     * fromArray, doOnCancel, doOnError, doOnComplete, doOnNext, doAfterNext, doOnsubscribe,
     * doAfterTerminated, doOnReqeust
     */
    private static void parallelFlowable(){
        Flowable.range(1, 1000).parallel()
                .runOn(Schedulers.computation())
                .map(integer -> {return integer.toString();})
                .sequential()
                .subscribe(str -> {
                    System.out.println(Thread.currentThread().getName() + "-" + str);
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
