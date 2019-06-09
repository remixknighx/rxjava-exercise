package com.bill.exercise.chapter2.chapter2_4;


import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class SwitchOnNext {

    public static void main(String[] args) {
        switchObserver().subscribe(s -> {
            System.out.println("switch: " + s);
        });
    }

    private static Observable<String> createObserver(Long index){
        return Observable.interval(1000, 1000, TimeUnit.MILLISECONDS, Schedulers.trampoline()).take(5)
                .map(aLong -> {return index + "-" + aLong;});
    }

    private static Observable<String> switchObserver(){
        return Observable.switchOnNext(Observable.interval(3000, TimeUnit.MILLISECONDS, Schedulers.trampoline()).take(3)
        .map(aLong -> {return createObserver(aLong);}));
    }

}
