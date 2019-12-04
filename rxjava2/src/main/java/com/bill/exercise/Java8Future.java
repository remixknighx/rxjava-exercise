package com.bill.exercise;

import java.util.concurrent.*;

/**
 * java8 Future和CompletableFuture用法
 */
public class Java8Future {

    public static void main( String[] args ) throws Exception {
        completableFuture();
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

    private static void completableFuture(){
         // 进行变换，apply用法
        String result = CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + " apply").join();
        System.out.println(result);

        // 进行消耗，accept用法
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(s -> System.out.println(s+" accept"));

        // 对上一步的计算结果不关心，执行下一个操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenRun(() -> System.out.println("hello run"));

        // 结合两个CompletionStage的结果，进行转化后返回
        String result4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "combine";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result4);

        // 不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("run after both"));

        // 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的转化操作
        String result5 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world cost 2000 ms";
        }), s -> s).join();
        System.out.println(result5);

        // 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的消耗操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world accept either";
        }), System.out::println);

        // 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world run after either"));

        // 当运行时出现了异常，可以通过exceptionally进行补偿。
        String result6 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello exception";
        }).join();
        System.out.println(result6);

        // 运行完成时，对结果的处理。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断
        String result7 = CompletableFuture.supplyAsync(() -> {
            //出现异常
            if (true) {
                throw new RuntimeException("handle一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return t.getMessage();
            }
            return s;
        }).join();
        System.out.println(result7);
    }

}
