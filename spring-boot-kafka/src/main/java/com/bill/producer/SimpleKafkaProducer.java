package com.bill.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author wangjf
 * @date 2019/7/14 0014.
 */
@Component("simpleProducer")
public class SimpleKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Transactional(rollbackFor = RuntimeException.class)
    public void sendMessage(String data){
        for (int i = 0; i < 3; i++) {
            ListenableFuture<SendResult<String, String>> result =  kafkaTemplate.send(kafkaTopic, "key" + i, data + i);
            result.addCallback(success -> {
                LOGGER.info("Kafka Producer RecordMetadata [{}]", success.getRecordMetadata().toString());
            }, err -> {
                LOGGER.error("Kafka Producer Error: ", err);
            });

        }
        LOGGER.info("向kafka topic [{}] 推送数据成功", kafkaTopic);
    }
}
