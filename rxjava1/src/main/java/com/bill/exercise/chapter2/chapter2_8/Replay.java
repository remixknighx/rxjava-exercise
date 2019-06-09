package com.bill.exercise.chapter2.chapter2_8;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * replay操作符返回一个ConnectableObservable对象并且可以缓存其发送过的数据，
 * 这样即使有订阅者在其发送数据之后进行订阅，也能收到其之前发送过的数据。
 * 对缓存的控制可以从时间和空间两个维度来实现
 *
 * @author wangjf
 * @date 2019/6/9 0009.
 */
public class Replay {

    public static void main(String[] args) {
//        ConnectableObservable<Long> obs = replayCountObserver();
        ConnectableObservable<Long> obs = replayTimeObserver();
        Action1<Long> action2 = aLong -> {
            System.out.println("action2: " + aLong);
        };
        Action1<Long> action1 = aLong -> {
            System.out.println("action1: " + aLong);
            if (aLong == 2L) {
                obs.subscribe(action2);
            }
        };

        obs.subscribe(action1);
        obs.connect();
    }

    private static ConnectableObservable<Long> replayCountObserver(){
        Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
        return obser.replay(2);
    }

    private static ConnectableObservable<Long> replayTimeObserver(){
        Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
        return obser.replay(1, TimeUnit.SECONDS);
    }

}
