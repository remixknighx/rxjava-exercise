package com.bill.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/10/5 0005.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockTest {

    @Autowired
    private DistributedLock distributedLock;

    @Test
    public void testAcquire() throws Exception {
        for (int i = 0; i < 3; i++) {
            new Thread("Thread " + i){
                @Override
                public void run() {
                    boolean lockResult = distributedLock.acquire();
                    if (lockResult) {
                        System.out.println("子线程" + Thread.currentThread().getName() + "获取锁结果" + lockResult);
                        try {
                            Thread.sleep(2000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("子线程" + Thread.currentThread().getName() + "执行任务成功");
                    }
                    distributedLock.release();
                }
            }.start();
        }

        Thread.sleep(10000);
    }

    public static void main(String[] args) throws Exception {
        final CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(400000)
                .retryPolicy(new ExponentialBackoffRetry(30000,3))
                .namespace("test")
                .build();
        client.start();

        for (int i = 0; i < 3; i++) {
            new Thread("Thread " + i) {
                @Override
                public void run() {
                    InterProcessMutex lock = new InterProcessMutex(client, "/lock");
                    try {
                        if (lock.acquire(10, TimeUnit.SECONDS)) {
                            try {
                                Thread.sleep(3000L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("子线程" + Thread.currentThread().getName() + "执行任务成功");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

    }

}