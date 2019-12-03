package com.bill.exercise.chapter2;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;

/**
 * Completable不发送数据，只有onComplete和onError事件
 */
public class CompletableExercise {

    public static void main(String[] args) {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                emitter.onError(new Exception("wrong"));
            }
        }).onErrorComplete().andThen(Observable.range(1, 5))
                .subscribe((Integer integer) -> {
                        System.out.println(integer);
                });
    }

}
