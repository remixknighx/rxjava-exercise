package com.bill.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "${kafka.topic.ds.hsgtsjnx}")
    public void listen(ConsumerRecord<String, String> record) {
    }
}
