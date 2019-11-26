package com.bill.exercise.chapter2.chapter2_4;


import rx.Observable;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

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
//        createObserver(1).subscribe(i -> {
//            System.out.println("result:" + i);
//        });

        combineLatestObserver().subscribe(i -> {
            System.out.println("CombineLatest:" + i);
        });

        combineListObserver().subscribe(i -> {
            System.out.println("CombineList:" + i);
        });
    }

    private static Observable<Long> createObserver(int index){
        return Observable.interval(500 * index, TimeUnit.MILLISECONDS, Schedulers.trampoline());
    }

    private static Observable<String> combineLatestObserver(){
        return Observable.combineLatest(Observable.just(1), Observable.just(2), (num1, num2) -> {
           return "left: " + num1 + " right: " + num2;
        });
    }

    private static Observable<String> combineListObserver() {
        List<Observable<Long>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(Observable.just((long)i));
        }
        return Observable.combineLatest(list, (Object... args) -> {
            String temp = "";
            for (Object i : args) {
                temp = temp + ":" + i;
            }
            return temp;
        });
    }

}
