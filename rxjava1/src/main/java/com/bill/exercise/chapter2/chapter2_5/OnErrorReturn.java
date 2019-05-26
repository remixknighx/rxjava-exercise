package com.bill.exercise.chapter2.chapter2_5;


import rx.Observable;
import rx.Subscriber;

/**
 * onErrorReturn操作符可以在发生错误时，让Observable发送一个预先定义好的数据并
 * 停止继续发送数据，正常结束整个过程。并且不触发Subscriber的onError事件
 *
 * @author wangjf
 * @date 2019/5/26 0026.
 */
public class OnErrorReturn {

    public static void main(String[] args) {
        onErrorReturnObserver().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onErrorReturn Error: " + e);
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
                        subscriber.onNext("onNext: " + i);
                    }
                }
            }
        });
    }

    private static Observable<String> onErrorReturnObserver(){
        return createObserver().onErrorReturn(throwable -> {return "onErrorReturn";});
    }

}
