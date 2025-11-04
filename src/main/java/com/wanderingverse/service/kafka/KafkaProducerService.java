package com.wanderingverse.service.kafka;

/**
 * @author lihui
 * @since 2025/8/12 10:15
 **/
public interface KafkaProducerService {


    /**
     * 发送字符串消息
     *
     * @param topic   topic
     * @param message message
     */
    void sendMessage(String topic, String message);


    /**
     * 发送带 Key 的字符串消息
     *
     * @param topic   topic
     * @param key     key
     * @param message message
     */
    void sendMessageWithKey(String topic, String key, String message);


    /**
     * 获取 Topic
     *
     * @param topic topic
     * @return String Topic
     */
    String getTopic(String topic);
}
