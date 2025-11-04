package com.wanderingverse.service.kafka.impl;

import com.wanderingverse.service.kafka.KafkaProducerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author lihui
 * @since 2025/8/12 10:15
 **/
@Slf4j
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private Environment environment;

    @Override
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(getTopic(topic), message);
        log.info("发送消息：{}", message);
    }

    @Override
    public void sendMessageWithKey(String topic, String key, String message) {
        kafkaTemplate.send(getTopic(topic), key, message);
    }

    @Override
    public String getTopic(String topic) {
        String topicProperty = environment.getProperty("spring.kafka.topic.name");
        if (!StringUtils.hasText(topic) && !StringUtils.hasText(topicProperty)) {
            throw new IllegalArgumentException("未指定任何 topic");
        } else if (!StringUtils.hasText(topic) && StringUtils.hasText(topicProperty)) {
            topic = topicProperty;
        }
        return topic;
    }
}
