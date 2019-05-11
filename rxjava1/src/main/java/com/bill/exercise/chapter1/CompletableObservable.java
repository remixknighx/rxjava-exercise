package com.bill.exercise.chapter1;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;

/**
 * CompletableObservable: 单个事件的生产者
 * Completable只发送错误和结束的事件，而不发送数据
 *
 * @author wangjf
 * @date 2019/5/11 0011.
 */
public class CompletableObservable {

    public static void main(String[] args) {
        CompletableSubscriber completableSubscriber = new CompletableSubscriber() {
            @Override
            public void onCompleted() {
                System.out.println("on complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("on error: " + e.getMessage());
            }

            @Override
            public void onSubscribe(Subscription d) {
                d.unsubscribe();
            }
        };

        Completable.error(new Exception("send error")).subscribe(completableSubscriber);
        Completable.complete().subscribe(completableSubscriber);
    }

}
