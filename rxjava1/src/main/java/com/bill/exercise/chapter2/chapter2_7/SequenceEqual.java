package com.bill.exercise.chapter2.chapter2_7;

import rx.Observable;

/**
 * sequenceEqual判断两个Observable发送的数据序列是否相同
 * 发送的数据相同，数据的序列相同，结束的状态相同
 * 如果全部相同则返回true，否则返回false
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class SequenceEqual {

    public static void main(String[] args) {
        Observable.sequenceEqual(Observable.just(1,2,3), Observable.just(1,2,3)).subscribe(result -> {
            System.out.println("SequenceEqual Result: " + result);
        });
    }

}
