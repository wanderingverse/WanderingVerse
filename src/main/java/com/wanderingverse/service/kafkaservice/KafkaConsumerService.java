package com.wanderingverse.service.kafkaservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author lihui
 * @since 2025/8/12 10:17
 **/
public interface KafkaConsumerService {


    /**
     * 监听一至多个 Topic
     *
     * @param message message
     */
    void consumeMessage(String message);

    /**
     * 监听并获取更详细信息
     *
     * @param record record
     */
    void consumeWithMetadata(ConsumerRecord<String, String> record);
}
