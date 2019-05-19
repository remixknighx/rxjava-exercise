package com.bill.exercise.chapter2;

import rx.Observable;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/5/19 0019.
 */
public class ObservableFilter2_3 {

    public static void main(String[] args) {
        debounceTest();
    }

    /**
     * debounce操作符是用来做限流的
     */
    private static void debounceTest(){
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .debounce(new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        return Observable.timer(aLong % 2 * 1500, TimeUnit.MILLISECONDS);
                    }
                });
    }

}
