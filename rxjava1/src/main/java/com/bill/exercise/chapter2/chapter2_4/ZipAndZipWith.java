package com.bill.exercise.chapter2.chapter2_4;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class ZipAndZipWith {

    public static void main(String[] args) {
        zipWithObserver().subscribe(s -> System.out.println("zipWith: " + s));
        zipWithIterableObserver().subscribe(s -> System.out.println("zipWithIterable: " + s));
    }

    private static Observable<String> createObserver(int index) {
        return Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.trampoline()).take(index)
                .map(aLong -> {return index + ":" + aLong;});
    }

    private static Observable<String> zipWithObserver(){
        return createObserver(2).zipWith(createObserver(3), (String s, String s2) -> {
            return s + "-" + s2;
        });
    }

    private static Observable<String> zipWithIterableObserver(){
        return Observable.zip(createObserver(2), createObserver(3), createObserver(4), (String s, String s2, String s3) -> {
            return s + "-" + s2 + "-" + s3;
        });
    }

}
