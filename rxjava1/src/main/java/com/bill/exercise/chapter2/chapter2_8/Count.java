package com.bill.exercise.chapter2.chapter2_8;

import rx.Observable;

/**
 * count操作符用来统计源Observable发送了多少个数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Count {

    public static void main(String[] args) {
        Observable.just(1,23,4).count().subscribe(countResult -> {
            System.out.println("countResult: " + countResult);
        });
    }

}
