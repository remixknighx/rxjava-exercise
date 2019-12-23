package com.bill.zk;

import com.bill.common.exception.ZkException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangjf
 * @date 2019/10/4 0004.
 */
@Component
public class ZkUtil {

    @Autowired
    private CuratorFramework client;

    public void createPermanentNode(String path, String data) {
        doCreateNode(path, data, CreateMode.PERSISTENT);
    }

    public void createTemporaryNode(String path, String data) {
        doCreateNode(path, data, CreateMode.EPHEMERAL);
    }

    public String getNode(String path) {
        try {
            return new String(client.getData().forPath(path));
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    public void updateNode(String path, String data) {
        try {
            client.setData().forPath(path, data.getBytes());
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    public void deleteNode(String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    /** private methods **/
    private void doCreateNode(String path, String data, CreateMode mode) {
        try {
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path, data.getBytes());
        } catch (Exception e) {
            throw new ZkException(e);
        }
    }

    //    public static void main(String[] args) throws Exception {
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
//        CuratorFramework client = CuratorFrameworkFactory.builder()
//                                    .connectString("127.0.0.1:2181")
//                                    .sessionTimeoutMs(5000)
//                                    .retryPolicy(retryPolicy)
//                                    .namespace("test")
//                                    .build();
//        client.start();
//
//        // create
//        System.out.println(client.create().forPath("/shendiao", "liuyifei".getBytes()));
//
//        // read
//        Stat stat = new Stat();
//        System.out.println(new String(client.getData().storingStatIn(stat).forPath("/shendiao")));
//        System.out.println("Cversion: " + stat.getCversion() + " Aversion: " + stat.getAversion());
//
//        // update
//        System.out.println(client.setData().forPath("/shendiao", "huangxiaoming".getBytes()));
//
//        // read
//        Stat stat2 = new Stat();
//        System.out.println(new String(client.getData().storingStatIn(stat2).forPath("/shendiao")));
//        System.out.println("Cversion: " + stat2.getCversion() + " Aversion: " + stat2.getAversion());
//
//        // delete
//        client.delete().forPath("/shendiao");
//
//        // read
//        System.out.println(client.checkExists().forPath("/shendiao"));
//    }

}
