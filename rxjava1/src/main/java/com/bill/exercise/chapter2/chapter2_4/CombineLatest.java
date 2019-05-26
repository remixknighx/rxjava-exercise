package com.bill.exercise.chapter2.chapter2_4;


import rx.Observable;
import rx.functions.FuncN;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * combineLatest操作符可以将2～9个Observable发送的数据组合起来，然后再发送出来
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class CombineLatest {

    public static void main(String[] args) {
        new CombineLatest().combineLatestObserver().subscribe(i -> {
            System.out.println("CombineLatest:" + i);
        });

        new CombineLatest().combineListObserver().subscribe(i -> {
            System.out.println("CombineList:" + i);
        });

        new CombineLatest().createObserver(1).subscribe(i -> {
            System.out.println("result:" + i);
        });
    }

    private Observable<Long> createObserver(int index){
        return Observable.interval(500 * index, TimeUnit.MILLISECONDS);
    }

    private Observable<String> combineLatestObserver(){
        return Observable.combineLatest(createObserver(1), createObserver(2), (num1, num2) -> {
           return "left: " + num1 + " right: " + num2;
        });
    }

    private Observable<String> combineListObserver(){
        List<Observable<Long>> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            list.add(createObserver(i));
        }
        return Observable.combineLatest(list, new FuncN<String>() {
            @Override
            public String call(Object... args) {
                String temp = "";
                for (Object i: args) {
                    temp = temp + ":" + i;
                }
                return temp;
            }
        });
    }

}
