package com.bill.exercise.chapter2.chapter2_7;


import rx.Observable;
import rx.functions.Func1;

/**
 * all操作符根据一个函数对源Observable发送的所有数据进行判断，
 * 最终返回的结果就是这个判断结果
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class AllOperate {

    public static void main(String[] args) {
        Observable.just(1,2,3,4,5).all((Integer integer) -> {
            return integer < 6;
        }).subscribe(result -> {
            System.out.println("all result " + result);
        });

        Observable.just(1,2,3,4,5,6).all((Integer integer) -> {
            return integer < 4;
        }).subscribe(result -> {
            System.out.println("not all result " + result);
        });
    }

}
