package com.bill.exercise;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class Java8Future {

    public static void main( String[] args ) throws Exception {
        futureExercise();
        futureTask();
    }

    private static void futureExercise() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "futureResult";
        });

        System.out.println("Main Thread Work");

        System.out.println(future.get());

        executor.shutdown();
    }

    private static void futureTask() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<>(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Future Task Result";
        });
        executor.execute(future);
        System.out.println(future.get());

        executor.shutdown();
    }

}
