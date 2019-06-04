package com.bill.exercise.chapter2;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/5/19 0019.
 */
public class ObservableFilter2_3 {

    public static void main(String[] args) {
        sampleAndThrottleFirstTest();
    }

    /**
     * debounce操作符是用来做限流的
     */
    private static void debounceTest(){
        Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.trampoline())
                .debounce(aLong -> {
                            return Observable.timer(aLong % 2 * 1500, TimeUnit.MILLISECONDS);
                        });
    }

    /**
     * distinct操作符的用处就是去重，所有重复的数据都会被过滤掉
     */
    private static void distinctTest(){
        Observable.just(1,2,3,4,5,4,3,2,1).distinct().subscribe(num -> System.out.println("distinct num: " + num));
    }

    /**
     * distinctUntilChanged作用是过滤连续重复的数据
     */
    private static void distinctUntilChangedTest(){
        Observable.just(1,2,3,3,4,4,4,3,2,1).distinctUntilChanged().subscribe(num -> System.out.println("distinct num: " + num));
    }

    /**
     * elementAt只会过滤出源Observable发送出来的顺序为N的数据
     */
    private static void elementAtTest(){
        Observable.just(1,2,3,3).elementAt(1).subscribe(num -> System.out.println("elementAt i num is: " + num));
    }

    /**
     * filter根据一个函数来进行过滤操作
     */
    private static void filterTest(){
        Observable.just(0,1,2,3,4,5).filter(num -> {return num < 3;}).subscribe(num -> {
            System.out.println("Filter num: " + num);
        });
    }

    /**
     * first操作符只会返回第一条或者满足条件的第一条数据
     * last同理
     */
    private static void firstTest(){
        Observable.just(0, 1,2,3,4,5).first(num -> {return num > 2;}).subscribe(num -> {
            System.out.println("FIRST: " + num);
        });
    }

    /**
     * skip操作符能够将源Observable发送的数据过滤掉前n项
     * take操作符只取前n项
     */
    private static void skipAndTakeTest(){
        Observable.just(0,1,2,3,4,5).skip(2).subscribe(num -> {
            System.out.println("skip: " + num);
        });

        Observable.just(0,1,2,3,4,5).take(2).subscribe(num -> {
            System.out.println("take: " + num);
        });
    }

    /**
     * sample操作符是取规定时间段内最后一个数据
     * throttleFirst操作符取的则是规定时间段内的第一个数据，其他的会被过滤掉
     */
    private static void sampleAndThrottleFirstTest(){
        Observable.interval(800, TimeUnit.MILLISECONDS, Schedulers.trampoline()).sample(1000, TimeUnit.MILLISECONDS).subscribe(i -> {
            System.out.println("sample: " + i);
        });

        Observable.interval(200, TimeUnit.MILLISECONDS, Schedulers.trampoline()).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(i -> {
            System.out.println("throttleFirst: " + i);
        });
    }

}
