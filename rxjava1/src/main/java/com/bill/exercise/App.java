package com.bill.exercise;

import rx.Observable;
import rx.Subscriber;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        createObservable().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
            }
        });
    }

    /**
     * 使用Observable.unsafeCreate方法创建Observable对象
     */
    private static Observable<Integer> createObservable(){
        return Observable.unsafeCreate(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i ++) {
                        int temp = new Random().nextInt(10);

                        if (temp > 8) {
                            subscriber.onError(new Exception("value > 8"));
                            break;
                        } else {
                            subscriber.onNext(temp);
                        }

                        // 无异常发生，正常结束
                        if (i == 0) {
                            subscriber.onCompleted();
                        }
                    }
                }
            }
        });
    }

}
