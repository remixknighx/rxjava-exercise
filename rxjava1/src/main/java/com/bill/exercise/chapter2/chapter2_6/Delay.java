package com.bill.exercise.chapter2.chapter2_6;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

/**
 * delay操作符就是让发送数据的时机延后一段时间，
 * 这样，所有的数据都会依次延后一段时间再发送
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Delay {

    public static void main(String[] args) {
        System.out.println("start delay subscribe:" + getCurrentTime());
        delayObserver().subscribe(aLong -> {System.out.println("delay: " + (getCurrentTime() - aLong));});

        System.out.println("start delayObserver subscribe:" + getCurrentTime());
        delaySubscriptionObserver().subscribe(aLong -> {
            System.out.println("delayObserver: " + aLong);
        });
    }

    private static long getCurrentTime(){
        return System.currentTimeMillis() / 1000;
    }

    private static Observable<Long> createObserver(int index){
        return Observable.create(new Observable.OnSubscribe<Long>() {
            @Override
            public void call(Subscriber<? super Long> subscriber) {
                System.out.println("subscribe: " + getCurrentTime());
                for (int i = 1; i <= index; i++) {
                    subscriber.onNext(getCurrentTime());
                }
            }
        });
    }

    private static Observable<Long> delayObserver(){
        return createObserver(2).delay(2, TimeUnit.SECONDS);
    }

    private static Observable<Long> delaySubscriptionObserver(){
        return createObserver(2).delay(2, TimeUnit.SECONDS);
    }


}
