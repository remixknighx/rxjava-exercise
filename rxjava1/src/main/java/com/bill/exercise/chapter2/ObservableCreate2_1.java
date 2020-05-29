package com.bill.exercise.chapter2;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/5/11 0011.
 */
public class ObservableCreate2_1 {

    public static void main(String[] args) {
//        List<String> testList = new ArrayList<>();
//        Observable.from(testList);
        System.out.println(File.pathSeparator);
        System.out.println(File.pathSeparatorChar);
        System.out.println(File.separator);
        System.out.println(File.separatorChar);
    }

    /**
     * range: 发送一个范围内的数据
     */
    private static void rangeExercise(){
        Observable.range(3,4).subscribe((integer) -> {
                System.out.println("on next integer: " + integer);
        });
    }

    /**
     * defer: 每次订阅都会得到一个刚创建的最新的Observable对象
     */
    private static void deferExercise(){
        Observable.defer(() -> {
            return Observable.just(System.currentTimeMillis());
        }).subscribe((aLong) -> {
            System.out.println("defer: " + aLong);
        });
    }

    /**
     * just操作符接收某个对象作为输入，然后会创建一个发送该对象的Observable
     */
    private static void justExercise(){
        Observable.just(System.currentTimeMillis()).subscribe((aLong) -> {
            System.out.println("just: " + aLong);
        });
    }

    /**
     * from操作符接收一个对象作为参数来创建Observable，参数对象可以是Iterable, Callable, Future和数组
     */
    private static void fromExercise(){
        Observable.from(new Integer[]{0, 1, 2, 3, 4, 5})
                .sample(1, TimeUnit.SECONDS, Schedulers.trampoline())
                .observeOn(Schedulers.io())
                .subscribe((integer) -> {
            System.out.println(Thread.currentThread().getName() + " single value: " + integer);
        });
    }

    /**
     * interval所创建的对象会从0开始，每隔固定的事件发送一个数字
     */
    private static void intervalExercise(){

        Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.trampoline()).take(3).subscribe(new Subscriber<Long>() {
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
        Observable.just(1,2,3).repeat().take(6).subscribe((Integer integer) -> {
            System.out.println("repeat integer: " + integer);
        });
    }

    /**
     * timer操作符创建的Observable会在指定时间后发送一个数字0，其默认也是运行在computation Scheduler上
     */
    private static void timerExercise(){
        Observable.timer(3, TimeUnit.SECONDS).subscribe((aLong) -> { System.out.println("timer: " + aLong); });
    }

}
