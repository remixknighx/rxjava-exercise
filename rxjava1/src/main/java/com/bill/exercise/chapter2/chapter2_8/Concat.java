package com.bill.exercise.chapter2.chapter2_8;

import rx.Observable;

/**
 * concat操作符将多个Observable结合成一个Observable并发送数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Concat {

    public static void main(String[] args) {
        concatObserver().subscribe(num -> {
            System.out.println("num: " + num);
        });
    }

    private static Observable<Integer> concatObserver(){
        Observable<Integer> obser1 = Observable.just(1,2,3);
        Observable<Integer> obser2 = Observable.just(4,5,6);
        Observable<Integer> obser3 = Observable.just(7,8,9);
        return Observable.concat(obser1, obser2, obser3);
    }

}
