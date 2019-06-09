package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * skipUntil根据一个标志Observable来判断的，当这个标志Observable没有发送数据的时候，
 * 所有源Observable发送的数据都会被跳过；当标志Observable发送了一个数据后，开始正常的发送数据
 *
 * skipWhile根据一个函数来判断，如果函数返回值为true，则跳过源Observable发送的数据
 * 如果是false，则正常发送数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class SkipUntilAndSkipWhile {

    public static void main(String[] args) {
        skipUntilObserver().subscribe(i -> {
            System.out.println("skipUntil" + i);
        });

        skipWhileObserver().subscribe(i -> {
            System.out.println("skipWhile: " + i);
        });
    }

    private static Observable<Long> skipUntilObserver(){
        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline()).skipUntil(Observable.timer(3, TimeUnit.SECONDS));
    }

    private static Observable<Long> skipWhileObserver(){
        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline()).skipWhile(aLong -> {return aLong < 5L;});
    }

}
