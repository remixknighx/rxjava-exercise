package com.bill.exercise.chapter2.chapter2_8;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Connectable Observable是一种特殊的Observable，并不是在订阅者订阅时才发送数据，
 * 而是只要对其应用connect操作符就开始发送数据
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class Connectable {

    public static void main(String[] args) {
        ConnectableObservable<Long> connectableObservable = publishObserver();
        refCountObservable(connectableObservable);

//        Action1<Long> action2 = aLong -> {System.out.println("action2: " + aLong);};
//
//        Action1<Long> action1 = aLong -> {
//            System.out.println("action1: " + aLong);
//            if (aLong == 2L) {
//                connectableObservable.subscribe(action2);
//            }
//        };
//
//        connectableObservable.subscribe(action1);
//        connectableObservable.connect();
    }

    /**
     * publish操作符就是用来将一个普通的Observable转化为一个ConnectableObservable的
     * connect操作符就是用来触发ConnectableObservable发送数据的
     */
    private static ConnectableObservable<Long> publishObserver(){
        Observable<Long> obser = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
        return obser.publish();
    }

    /**
     * refCount操作符能够将一个ConnectableObservable对象再重新转化为一个普通的Observable对象
     */
    private static void refCountObservable(ConnectableObservable<Long> connObs){
        connObs.refCount().subscribe(aLong -> {
            System.out.println("refcount: " + aLong);
        });

    }

}
