package com.bill.exercise.chapter1;

import rx.Single;
import rx.SingleSubscriber;

/**
 * Single: 单个数据的生产者
 * Single会调用SingleSubscriber的两个方法来发送数据和事件
 *
 * 1. onSuccess: 发送Single唯一的数据，只能调用一次
 * 2. onError: Single内部有异常或者错误时，调用此接口发送异常事件，同样只能调用一次
 *
 * @author wangjf
 * @date 2019/5/11 0011.
 */
public class SingleObservable {

    public static void main(String[] args) {
        emitValue();
    }

    private static void emitValue(){
        final int value = 5;
        Single.create(new Single.OnSubscribe<Integer>() {
            @Override
            public void call(SingleSubscriber<? super Integer> singleSubscriber) {
                if (!singleSubscriber.isUnsubscribed()) {
//                    发送数据
//                    singleSubscriber.onSuccess(value);

//                    发送事件
                    singleSubscriber.onError(new Exception("发送事件"));
                }
            }
        }).subscribe(new SingleSubscriber<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                System.out.println("Success: " + integer);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println("Error: " + error.getMessage());
            }
        });
    }

}
