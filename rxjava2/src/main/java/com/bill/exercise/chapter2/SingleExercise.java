package com.bill.exercise.chapter2;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Single只有onSucces和onError
 */
public class SingleExercise {

    public static void main(String[] args) {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> emitter) throws Exception {
                emitter.onSuccess("single success");
            }
        }).subscribe((s) -> {
            System.out.println(s);
        }, (Throwable throwable) -> {
            System.out.println("error: " + throwable);
        });
    }

}
