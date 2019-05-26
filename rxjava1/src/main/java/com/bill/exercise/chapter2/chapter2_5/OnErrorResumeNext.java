package com.bill.exercise.chapter2.chapter2_5;

import rx.Observable;
import rx.Subscriber;

/**
 * onErrorResumeNext在有错误发生的时候，会创建另外一个Observable来
 * 代替当前的Observable并继续发送数据
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class OnErrorResumeNext {

    public static void main(String[] args) {
        onErrorResumeNextObserver().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });
    }

    private static Observable<String> createObserver(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 6; i++) {
                    if(i == 3){
                        subscriber.onError(new Exception("Throw Error"));
                    } else {
                        subscriber.onNext(String.valueOf(i));
                    }
                }
            }
        });
    }

    private static Observable<String> onErrorResumeNextObserver(){
        return createObserver().onErrorResumeNext(Observable.just("7","8","9"));
    }

}
