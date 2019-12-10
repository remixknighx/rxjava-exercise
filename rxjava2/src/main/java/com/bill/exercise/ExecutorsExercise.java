package com.bill.exercise;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author wangjianfeng
 */
public class ExecutorsExercise {

    public static void main(String[] args) throws InterruptedException {
        synchronousQueue();
    }

    /**
     * FIFO 无界队列LinkedBlockingQueue
     */
    private static void blockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        // 阻塞
        queue.take();
        queue.put("");
        // 非阻塞
        queue.poll();
        queue.offer("");
    }

    /**
     * FIFO 有界队列ArrayBlockingQueue
     */
    private static void arrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        // add,offer 非阻塞
        queue.offer("abc");
        queue.poll();
        // 阻塞
        queue.put("efg");
        queue.take();
    }

    /**
     * 等待队列 SynchronousQueue, 无数据缓冲
     * @link https://zhuanlan.zhihu.com/p/29227508
     */
    private static void synchronousQueue() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        Thread putThread = new Thread(() -> {
            System.out.println("put thread start");
            try {
                queue.put(1);
            } catch (InterruptedException e) {
            }
            System.out.println("put thread end");
        });

        Thread takeThread = new Thread(() -> {
            System.out.println("take thread start");
            try {
                System.out.println("take from putThread: " + queue.take());
            } catch (InterruptedException e) {
            }
            System.out.println("take thread end");
        });

        putThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeThread.start();
    }

    private static void selfExecutor(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,4,0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2), new ThreadFactoryBuilder().setNameFormat("self-thread-").build());
        for (int i = 0; i < 7; i++) {
            final String intName = String.valueOf(i);
            executor.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-name: " + intName);
            });
        }
        executor.shutdown();
    }

}
