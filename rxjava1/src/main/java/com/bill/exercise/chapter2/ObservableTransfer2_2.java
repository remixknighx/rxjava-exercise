package com.bill.exercise.chapter2;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjf
 * @date 2019/5/19 0019.
 */
public class ObservableTransfer2_2 {

    public static void main(String[] args) {
        windowExercise();
    }

    /**
     * buffer操作符所要做的就是将数据按照规定的大小做一下缓存，当缓存的数据量达到设置的上限后就将缓存的数据作为一个集合发送出去
     * skip设置跳过的数据，count设置发送的数目
     */
    private static void bufferExercise(){
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8,9)
                .buffer(2,3)
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> longs) {
                        System.out.println("bufferTime: " + longs);
                    }
                });
    }

    /**
     * flatMap将多个Observable发送的数据整合并根据想要的规则进行转化后再发送出去
     */
    private static void flatMapExercise(){
        Observable.just(1,2,3)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        return Observable.just("flat map:" + integer);
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                }
        });
    }

    /**
     * groupBy操作符会将源Observable发送的数据按照key来拆分成一些小的Observable，
     * 然后这些小的Observable发送其包含的数据，类似SQL里面的groupBy
     */
    private static void groupByExercise(){
        Observable.just(1,2,3,4,5,6,7,8,9)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer % 2;
                    }
                }, new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "groupByKeyValue: " + integer;
                    }
                }).subscribe(new Action1<GroupedObservable<Integer, String>>() {
                    @Override
                    public void call(GroupedObservable<Integer, String> integerStringGroupedObservable) {
                        System.out.println(integerStringGroupedObservable.getKey());
                        if (integerStringGroupedObservable.getKey() == 0) {
                            integerStringGroupedObservable.subscribe(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    System.out.println(s);
                                }
                            });
                        }
                }
        });
    }

    /**
     * map将Observable发送的每个数据都按照给定的函数进行转化，并将转化后的数据发送出来
     */
    private static void mapExercise(){
        Observable.just(1,2,3).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * 10;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("map:" + integer);
            }
        });
    }

    /**
     * scan操作符对一个序列的数据应用另一个函数进行计算，并将这个函数的结果发送出去，
     * 作为下一个数据应用这个函数时的第一个参数使用
     */
    private static void scanExercise(){
        List<Integer> listNum = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNum.add(2);
        }

        Observable.from(listNum).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer x, Integer y) {
                return x * y;
            }
        }).subscribe((Action1) result -> {System.out.println(result);});
    }

    /**
     * window类似于buffer，window发送的是一些小的Observable对象
     */
    private static void windowExercise(){
        Observable.just(1,2,3,4,5,6,7,8,9).window(3)
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        System.out.println(integerObservable.getClass().getName());
                        integerObservable.subscribe((Action1) num -> {
                            System.out.println(num);
                        });
                    }
                });
    }

}
