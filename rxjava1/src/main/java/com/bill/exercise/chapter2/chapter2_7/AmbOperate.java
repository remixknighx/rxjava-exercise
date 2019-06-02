package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * amb操作符可以将多个Observable结合起来，让它们竞争，
 * 哪个Observable发送了数据，就继续发送这个Observable的数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class AmbOperate {

    public static void main(String[] args) {
        ambObserver().subscribe(integer -> {
            System.out.println(integer);
        });
    }

    private static Observable<Integer> ambObserver(){
        Observable<Integer> delay3 = Observable.just(1,2,3).delay(3000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(4,5,6).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay1 = Observable.just(7,8,9).delay(1000, TimeUnit.MILLISECONDS);
        return Observable.amb(delay1, delay2, delay3);
    }

}
