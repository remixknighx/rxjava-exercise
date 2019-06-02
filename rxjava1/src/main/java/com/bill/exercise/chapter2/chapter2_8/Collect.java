package com.bill.exercise.chapter2.chapter2_8;

import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;

import java.util.ArrayList;
import java.util.List;

/**
 * collect操作符用来将源Observable发送的数据收集到一个数据结构里面，
 * 最后将这个数据结构整个发送出来
 *
 * 第一个函数会产生收集数据结构的函数
 * 第二个函数会将上面函数产生的数据结构和源Observable发送的数据作为参数，
 * 且这个函数会将源Observable发送的数据存入到这个数据结构中
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Collect {

    public static void main(String[] args) {
        collectObserver().subscribe(result -> {
            System.out.println("collect result " + result);
        });
    }

    private static Observable<ArrayList<Integer>> collectObserver(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        return Observable.from(list).collect(ArrayList::new, (templist, single) -> {templist.add(single);});
    }

}
