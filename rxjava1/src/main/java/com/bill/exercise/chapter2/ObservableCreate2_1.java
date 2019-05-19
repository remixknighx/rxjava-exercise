package com.bill.exercise.chapter2;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/5/11 0011.
 */
public class ObservableCreate2_1 {

    public static void main(String[] args) {
        timerExercise();
    }

    /**
     * range: 发送一个范围内的数据
     */
    private static void rangeExercise(){
        Observable.range(3,4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("on next integer: " + integer);
            }
        });
    }

    /**
     * defer: 每次订阅都会得到一个刚创建的最新的Observable对象
     */
    private static void deferExercise(){
        Observable<Long> deferObservable = Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return Observable.just(System.currentTimeMillis());
            }
        });
        Action1<Long> subscriber = new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("defer: " + aLong);
            }
        };
        deferObservable.subscribe(subscriber);
    }

    /**
     * just操作符接收某个对象作为输入，然后会创建一个发送该对象的Observable
     */
    private static void justExercise(){
        Observable.just(System.currentTimeMillis()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("just: " + aLong);
            }
        });
    }

    /**
     * from操作符接收一个对象作为参数来创建Observable，参数对象可以是Iterable, Callable, Future和数组
     */
    private static void fromExercise(){
        Observable.from(new Integer[]{0, 1, 2, 3, 4, 5}).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("single value: " + integer);
            }
        });
    }

    /**
     * interval所创建的对象会从0开始，每隔固定的事件发送一个数字
     */
    private static void intervalExercise(){
        Observable.interval(3, TimeUnit.SECONDS).observeOn(Schedulers.io()).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("on complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("on error: " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("on next value: " + aLong);
            }
        });
    }

    /**
     * repeat操作符可以让Observable对象发送的数据重复发送N次
     */
    private static void repeatExercise(){
        Observable.just(1,2,3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("repeat integer: " + integer);
            }
        });
    }

    /**
     * timer操作符创建的Observable会在指定时间后发送一个数字0，其默认也是运行在computation Scheduler上
     *
     */
    private static void timerExercise(){
        Observable.timer(1, TimeUnit.SECONDS).observeOn(Schedulers.computation()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println("timer: " + aLong);
            }
        });
    }

}
