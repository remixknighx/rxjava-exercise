package com.bill.vertx.core;

public class TaskQueueTest {

    private Runnable runner;

    public TaskQueueTest(){
        runner = this::run;
    }

    private void run(){
        System.out.println("run");
    }

    public void runRunner(){
        runner.run();
    }

}
