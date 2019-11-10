//package com.bill.consumer;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
///**
// * @author wangjf
// * @date 2019/7/28 0028.
// */
//@Component("simpleConsumer")
//public class SimpleKafkaConsumer {
//
//    private static Logger LOGGER = LoggerFactory.getLogger(SimpleKafkaConsumer.class);
//
//    @KafkaListener(topics = {"${kafka.topic1}", "${kafka.topic2}"})
//    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
//        LOGGER.info("Thread [{}] consumes topic [{}], key [{}], offset [{}], partition [{}], data [{}]",
//                new Object[]{
//                        Thread.currentThread().getName(),
//                        consumerRecord.topic(),
//                        consumerRecord.key(),
//                        consumerRecord.offset(),
//                        consumerRecord.partition(),
//                        consumerRecord.value()});
//        acknowledgment.acknowledge();
//    }
//}
