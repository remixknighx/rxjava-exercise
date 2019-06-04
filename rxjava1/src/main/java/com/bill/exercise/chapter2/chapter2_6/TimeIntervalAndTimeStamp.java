package com.bill.exercise.chapter2.chapter2_6;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;
import rx.schedulers.Timestamped;

import java.util.concurrent.TimeUnit;

/**
 * timeInterval会拦截源Observable发送出来的数据，将其封装为一个TimeInterval对象。
 * TimeInterval对象内部包含Observable发送的原始数据，以及发送当前数据和发送上一个数据的时间间隔
 *
 * t同样会将每个数据项重新包装成一个TimeStamp对象，TimeStamp对象内包含了原始数据和一个时间戳，
 * 这个时间戳标明了每次数据发送的事件
 *
 * @author wangjf
 * @date 2019/6/2 0002.
 */
public class TimeIntervalAndTimeStamp {

    public static void main(String[] args) {
        timeIntervalObserver().subscribe(aLong -> {
            System.out.println("timeInterval:" + aLong.getValue() + " - " + aLong.getIntervalInMilliseconds());
        });

        timeStampObserver().subscribe(aLong -> {
            System.out.println("timeStamp: " + aLong.getValue() + " - " + aLong.getTimestampMillis());
        });
    }

    private static Observable<TimeInterval<Long>> timeIntervalObserver(){
        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline()).take(3).timeInterval();
    }

    private static Observable<Timestamped<Long>> timeStampObserver(){
        return Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline()).take(3).timestamp();
    }

}
