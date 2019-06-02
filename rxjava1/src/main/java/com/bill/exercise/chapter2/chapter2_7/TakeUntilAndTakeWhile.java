package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * takeUntil和takeWhile与skipUntil和skipWhile完全相反
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class TakeUntilAndTakeWhile {

    public static void main(String[] args) {
        takeUntilObserver().subscribe(i -> {
            System.out.println("takeUntil: " + i);
        });

        takeWhileObserver().subscribe(i -> {
            System.out.println("takeWhile: " + i);
        });
    }

    private static Observable<Long> takeUntilObserver(){
        return Observable.interval(1, TimeUnit.SECONDS).takeUntil(Observable.timer(3, TimeUnit.SECONDS));
    }

    private static Observable<Long> takeWhileObserver(){
        return Observable.interval(1,TimeUnit.SECONDS).takeWhile(result -> {return result < 5;});
    }

}
