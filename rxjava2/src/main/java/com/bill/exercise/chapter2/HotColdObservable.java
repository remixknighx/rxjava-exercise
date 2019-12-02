package com.bill.exercise.chapter2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;

/**
 * Hot Observable无论有没有观察者进行订阅，事件始终都会发生。
 * Hot Observable与订阅者们的关系是一对多的关系。
 *
 * Cold Observable是只有观察者订阅了，才开始执行发射数据流的代码
 * Cold Observable和Observer只能是一对一的关系。当有多个Observer的时候，它们各自的事件是独立的.
 */
public class HotColdObservable {

    public static void main(String[] args) {
        refCountObservable();
    }

    private static void coldObservable(){
        Consumer<Long> subscriber1 = (aLong) -> {
            System.out.println("subscriber1: " + aLong);
        };
        Consumer<Long> subscriber2 = (aLong) -> {
            System.out.println("subscriber2: " + aLong);
        };

        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                .take(Integer.MAX_VALUE).subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread());

        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. 通过refCount将Hot Observable转换成Cold Observable
     * 2. share = publish().refCount()
     */
    private static void refCountObservable(){
        Consumer<Long> subscriber1 = (aLong) -> {
            System.out.println("subscriber1: " + aLong);
        };
        Consumer<Long> subscriber2 = (aLong) -> {
            System.out.println("subscriber2: " + aLong);
        };

        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE).subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();
        connectableObservable.connect();

        Observable<Long> observable = connectableObservable.refCount();

        Disposable disposable1 = observable.subscribe(subscriber1);
        Disposable disposable2 = observable.subscribe(subscriber2);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        disposable1.dispose();
        disposable2.dispose();

        System.out.println("restart");

        disposable1 = observable.subscribe(subscriber1);
        disposable2 = observable.subscribe(subscriber2);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用publish将Cold Observable转换成Hot Observable
     */
    private static void publishHotObservable(){
        Consumer<Long> subscriber1 = (aLong) -> {
            System.out.println("subscriber1: " + aLong);
        };
        Consumer<Long> subscriber2 = (aLong) -> {
            System.out.println("subscriber2: " + aLong);
        };
        Consumer<Long> subscriber3 = (aLong) -> {
            System.out.println("subscriber3: " + aLong);
        };

        ConnectableObservable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE).subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();

        observable.connect();

        observable.subscribe(subscriber1);
        observable.subscribe(subscriber2);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(subscriber3);
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用Subject和Processor实现Hot Observable，前者不支持背压，后者支持背压控制
     */
    private static void subjectProcessorHotObservable(){
        Consumer<Long> subscriber1 = (aLong) -> {
            System.out.println("subscriber1: " + aLong);
        };
        Consumer<Long> subscriber2 = (aLong) -> {
            System.out.println("subscriber2: " + aLong);
        };
        Consumer<Long> subscriber3 = (aLong) -> {
            System.out.println("subscriber3: " + aLong);
        };

        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE).subscribe(emitter::onNext);
            }
        }).observeOn(Schedulers.newThread());


        PublishSubject<Long> subject = PublishSubject.create();
        observable.subscribe(subject);

        subject.subscribe(subscriber1);
        subject.subscribe(subscriber2);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subject.subscribe(subscriber3);
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
