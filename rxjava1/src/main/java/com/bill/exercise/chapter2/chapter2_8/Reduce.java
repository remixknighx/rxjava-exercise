package com.bill.exercise.chapter2.chapter2_8;

import rx.Observable;
import rx.functions.Func2;

import java.util.ArrayList;
import java.util.List;

/**
 * reduce操作符应用一个函数接收Observable发送的数据和函数的计算结果
 * 作为下次计算的参数，并输出最后的结果
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Reduce {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Observable.from(list).reduce((Integer x, Integer y) -> {
            System.out.println("x: " + x + " y: " + y);
            return x * y;
        }).subscribe(finalResult -> {
            System.out.println("finalResult: " + finalResult);
        });

    }

}
