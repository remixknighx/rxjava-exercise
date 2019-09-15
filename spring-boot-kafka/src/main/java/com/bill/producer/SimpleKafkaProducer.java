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

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjf
 * @date 2019/7/14 0014.
 */
@Component("simpleProducer")
public class SimpleKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic1}")
    private String kafkaTopic1;
    @Value("${kafka.topic2}")
    private String kafkaTopic2;

    @Transactional(rollbackFor = RuntimeException.class)
    public boolean sendMessage(String data){
        List<ListenableFuture<SendResult<String, String>>> results = new ArrayList<>();
        results.add(kafkaTemplate.send(kafkaTopic1, "key1", data + "-first"));
        results.add(kafkaTemplate.send(kafkaTopic2, "key2", data + "-second"));
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            results.get(finalI).addCallback(success -> {
                LOGGER.info("Kafka Producer push to topic [{}] success", success.getProducerRecord().topic());
            }, err -> {
                LOGGER.error("Kafka Producer Error: ", err);
            });
        }

        return true;

    }
}
