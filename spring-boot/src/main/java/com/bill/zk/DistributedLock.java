package com.bill.zk;

import com.bill.common.exception.ZkException;
import com.bill.config.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjf
 * @date 2019/10/5 0005.
 */
@Component
public class DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLock.class);
    @Autowired
    private ZkConfig zkConfig;
    private final String LOCK_PATH = "/lock";

    public boolean acquire() {
        try {
            InterProcessMutex lock = new InterProcessMutex(createZkClient(), LOCK_PATH);
            boolean lockResult = lock.acquire(10, TimeUnit.SECONDS);
            if (lockResult) {
                LOGGER.info("[{}] acquire lock success! ", Thread.currentThread().getName());
            } else {
                LOGGER.info("[{}] acquire lock failure! ", Thread.currentThread().getName());
            }
            return lockResult;
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    public void release() {
        try {
            InterProcessMutex lock = new InterProcessMutex(createZkClient(), LOCK_PATH);
            if (lock != null) {
                lock.release();
                LOGGER.info("[{}] release lock success", Thread.currentThread().getName());
            } else {
                LOGGER.warn("[{}] release lock failure", Thread.currentThread().getName());
            }
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    private CuratorFramework createZkClient(){
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getZkUrl())
                .sessionTimeoutMs(400000)
                .retryPolicy(new ExponentialBackoffRetry(30000,3))
                .namespace(zkConfig.getZkDefaultPath())
                .build();
        client.start();
        return client;
    }

}
