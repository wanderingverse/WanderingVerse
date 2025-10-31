package com.wanderingverse.service.kafkaservice.impl;

import com.wanderingverse.service.kafkaservice.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

/**
 * @author lihui
 * @since 2025/8/12 10:17
 **/
@Slf4j
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    @Override
//    @KafkaListener(topics = {"${spring.kafka.topic.name}"})
    public void consumeMessage(String message) {
        log.info("接收消息：{}", message);
    }

    @Override
//    @KafkaListener(topics = "${spring.kafka.topic.name}")
    public void consumeWithMetadata(ConsumerRecord<String, String> record) {
        log.info("Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}", record.topic(), record.partition(), record.offset(), record.key(), record.value());
    }
}
