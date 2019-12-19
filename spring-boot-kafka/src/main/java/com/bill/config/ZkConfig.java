package com.bill.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjf
 * @date 2019/10/4 0004.
 */
@Configuration
public class ZkConfig {

    @Value("${zookeeper.url:127.0.0.1:2181}")
    private String zkUrl;
    @Value("${zookeeper.default_path:test}")
    private String zkDefaultPath;

    @Bean
    public CuratorFramework getCuratorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(30000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(400000)
                .retryPolicy(retryPolicy)
                .namespace(zkDefaultPath)
                .build();
        client.start();
        return client;
    }

}
