package com.ethen.kafka.spring.producer.controller;

import com.alibaba.fastjson.JSONObject;
import com.ethen.kafka.spring.constant.TopicConstant;
import com.ethen.kafka.spring.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 自定义分区器
 * <p>
 * 我们知道，kafka中每个topic被划分为多个分区，那么生产者将消息发送到topic时，具体追加到哪个分区呢？这就是所谓的分区策略，
 * Kafka 为我们提供了默认的分区策略，同时它也支持自定义分区策略。其路由机制为：
 * ① 若发送消息时指定了分区（即自定义分区策略），则直接将消息append到指定分区；
 * ② 若发送消息时未指定 patition，但指定了 key（kafka允许为每条消息设置一个key），则对key值进行hash计算，根据计算结果
 * 路由到指定分区，这种情况下可以保证同一个 Key 的所有消息都进入到相同的分区；
 * ③ partition 和 key 都未指定，则使用kafka默认的分区策略，轮询选出一个 partition
 */
@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/v1/kafka/message")
    public void sendMessage(@RequestBody MessageVO message) {
        kafkaTemplate.send(TopicConstant.SPRING_KAFKA_TOPIC_CHAP_01, message);
    }

    @PostMapping("/v2/kafka/message")
    public void sendMessageCallback(@RequestBody MessageVO message) {
        Objects.requireNonNull(message);
        String topic = StringUtils.isEmpty(message.getTopic()) ? TopicConstant.SPRING_KAFKA_TOPIC_CHAP_01 : message.getTopic();
        kafkaTemplate.send(topic, message.getPartition(), message.getKey(), message.getValue())
                .addCallback(success -> {
                            assert success != null;
                            System.err.println("消息发送成功：topic:" + success.getRecordMetadata().topic()
                                    + ",partition:" + success.getRecordMetadata().partition()
                                    + ",offset:" + success.getRecordMetadata().offset());
                        }, fail -> System.err.println("消息发送失败：" + fail.getMessage())
                );
    }
}
