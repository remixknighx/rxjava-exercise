package com.bill.vertx.core;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;



public class VertxSample {

    public static void main(String[] args) {
        Vertx vertx =  Vertx.vertx(new VertxOptions());
//        vertx.setTimer(1000, id -> {
//            System.out.println("time fired");
//        });

        TaskQueueTest taskQueueTest = new TaskQueueTest();
        taskQueueTest.runRunner();
//        vertx.executeBlocking(future -> {
//            String result = "result";
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            future.complete(result);
//        }, res -> {
//            System.out.println("The result is " + res.result());
//        });

        System.out.println("print");
    }

    private static void testPrivate(){
        System.out.println("test private");
    }

}
