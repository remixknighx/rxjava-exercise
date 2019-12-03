package com.bill.exercise.chapter2;

import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * Subject是线程不安全的。
 * Processor和Subject作用相同，前者支持背压
 */
public class SubjectExercise {

    public static void main(String[] args) {
        publishSubject();
    }

    /**
     * 不论订阅在何时，只接收最后一条消息
     */
    private static void asyncSubject(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("async 1");
        subject.onNext("async 2");
        subject.onComplete();

        subject.subscribe((s) -> {
            System.out.println(s);
        }, (Throwable e) -> {
            System.out.println(e);
        }, () -> {
            System.out.println("onComplete");
        });


    }

    /**
     * 接收订阅前发送的最后一个消息及订阅后的所有消息
     */
    private static void behaviorSubject(){
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject");
        subject.onNext("subject1");
        subject.onNext("subject2");
        subject.subscribe((s) -> {
            System.out.println("behavior: " + s);
        }, (Throwable e) -> {
            System.out.println(e);
        }, () -> {
            System.out.println("Behavior onComplete");
        });
        subject.onNext("subject3");
        subject.onNext("subject4");
        subject.onComplete();
    }

    /**
     * 发送所有来自原始Observable的数据给观察者，无论是何时订阅的
     */
    private static void replaySubject(){
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("subject1");
        subject.onNext("subject2");
        subject.subscribe((s) -> {
            System.out.println("replay: " + s);
        }, (Throwable e) -> {
            System.out.println(e);
        }, () -> {
            System.out.println("replay onComplete");
        });
        subject.onNext("subject3");
        subject.onNext("subject4");
        subject.onComplete();
    }

    /**
     * 发送订阅之后全部数据
     */
    private static void publishSubject(){
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("subject1");
        subject.onNext("subject2");
        subject.subscribe((s) -> {
            System.out.println("publish: " + s);
        }, (Throwable e) -> {
            System.out.println(e);
        }, () -> {
            System.out.println("publish onComplete");
        });
        subject.onNext("subject3");
        subject.onNext("subject4");
        subject.onComplete();
    }

}
