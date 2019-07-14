package com.bill.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjf
 * @date 2019/7/14 0014.
 */
@Component
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Transactional(rollbackFor = RuntimeException.class)
    public void sendMessage(String data){
        kafkaTemplate.send(kafkaTopic, data);
        LOGGER.info("向kafka topic [{}] 推送数据成功", kafkaTopic);
    }
}
