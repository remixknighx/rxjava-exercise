//package com.bill.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.listener.ContainerProperties;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 功能说明: <br>
// * 系统说明: <br>
// * 模块说明: <br>
// * 功能描述: <br>
// * - 输入:
// * - 输出:
// * <br>
// * 系统版本: <br>
// * 开发人员: wangjianfeng@orientsec.com.cn <br>
// * 创建时间：2019/7/1
// * 软件著作: 东方证券 版权所有<br>
// */
//@EnableKafka
//@Configuration
//public class KafkaConfig {
//
//    @Value("${app.kafka.bootstrap.servers}")
//    private String kafkaServersConfig;
//    @Value("${module.name}")
//    private String moduleName;
//
//    /**--------------- consumer config ------------------**/
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>(16);
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServersConfig);
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, moduleName + "-consumer1");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, moduleName + "-first_topic");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        DefaultKafkaConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(consumerConfigs());
//        return factory;
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setConcurrency(3);
//        factory.setBatchListener(false);
//        factory.getContainerProperties().setPollTimeout(3000);
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//        return factory;
//    }
//
//    /**--------------- producer config ------------------**/
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>(16);
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServersConfig);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, moduleName + "-producer1");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(producerConfigs());
//        factory.transactionCapable();
//        factory.setTransactionIdPrefix("trans-1-");
//        return factory;
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<String, String>(producerFactory());
//    }
//
//}
