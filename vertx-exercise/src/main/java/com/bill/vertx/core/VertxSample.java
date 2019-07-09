package com.bill.vertx.core;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxSample {

    public static void main(String[] args) {
        Vertx vertx =  Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        vertx.setTimer(1000, id -> {
            System.out.println("time fired");
        });
    }

}
