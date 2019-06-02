package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;

/**
 * contains操作符用来判断源Observable所发送的所有数据是否包含某一个数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Contains {

    public static void main(String[] args) {
        Observable.just(1,2,3).contains(3).subscribe(result -> {
            System.out.println("contains result " + result);
        });
    }

}
