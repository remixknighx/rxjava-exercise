package com.bill.zk;

import org.apache.curator.RetryPolicy;
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
        for (int i = 0; i < 1; i++) {
            new Thread("Thread " + i){
                @Override
                public void run() {
                    boolean lockResult = distributedLock.acquire();
                    System.out.println("子线程" + Thread.currentThread().getName() + "获取锁结果" + lockResult);
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行任务成功");
                    distributedLock.release();
                }
            }.start();
        }
    }

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("test")
                .build();
        client.start();

        InterProcessMutex lock = new InterProcessMutex(client, "/lock");
        lock.acquire(3, TimeUnit.SECONDS);
        try {
            Thread.sleep(1000);
            System.out.println("执行任务完成");
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            lock.release();
        }
    }

}