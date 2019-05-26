package com.bill.exercise.chapter2.chapter2_5;

import rx.Observable;
import rx.Subscriber;

/**
 * onExceptionResumeNext与onErrorResumeNext类似，
 * 不同之处在于其会对抛出的错误的数据类型进行判断
 * 若是Exception，就会使用另外一个Observable代替原Observable继续发送数据
 * 否则将错误发送给Subscriber
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class OnExceptionResumeNext {

    public static void main(String[] args) {
        onExceptionResumeObserver(false).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });
    }

    private static Observable<String> createObserver(Boolean createException){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 6; i++) {
                    if (i < 3) {
                        subscriber.onNext("onNext: " + i);
                    } else if(createException) {
                        subscriber.onError(new Exception("Exception"));
                    } else {
                        subscriber.onError(new Throwable("Throw error"));
                    }
                }
            }
        });
    }

    private static Observable<String> onExceptionResumeObserver(boolean isException) {
        return createObserver(isException).onExceptionResumeNext(Observable.just("7", "8"));
    }

}
