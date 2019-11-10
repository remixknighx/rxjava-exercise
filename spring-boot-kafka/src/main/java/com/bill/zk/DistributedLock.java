package com.bill.zk;

import com.bill.common.exception.ZkException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
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
    private CuratorFramework client;

    private final String LOCK_PATH = "/lock";
    private ThreadLocal<InterProcessLock> lockGroups = new ThreadLocal<>();

    public boolean acquire() {
        InterProcessLock lock = new InterProcessMutex(client, LOCK_PATH);

        try {
            lockGroups.set(lock);
            LOGGER.info("[{}] acquire lock success! ", Thread.currentThread().getName());
            return lock.acquire(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    public void release() {
        InterProcessLock lock = lockGroups.get();
        try {
            lock.release();
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

}
