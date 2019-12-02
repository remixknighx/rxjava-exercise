package com.bill.exercise.chapter2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObservableTest {

    public static void main(String[] args) {
        Observable.just("hello world").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        Observable.just("Thank you").subscribe((String s) -> {
            System.out.println(s);
        }, (Throwable throwable) -> {
            System.out.println(throwable);
        }, () -> {
            System.out.println("thank you onComplete");
        }, (Disposable disposable) -> {
            System.out.println("subscribe thank you");
        });
    }

}
