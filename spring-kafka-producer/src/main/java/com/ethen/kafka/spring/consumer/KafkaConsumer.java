package com.ethen.kafka.spring.consumer;

import com.ethen.kafka.spring.constant.GroupConstant;
import com.ethen.kafka.spring.constant.TopicConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    /**
     * 消费者
     *
     * @param record 消息记录
     */
    @KafkaListener(topics = {TopicConstant.SPRING_KAFKA_TOPIC_CHAP_01}, groupId = GroupConstant.SPRING_KAFKA_CONSUMER_GROUP_01)
    public void onMessage(ConsumerRecord<String, Object> record) {
        System.err.println("kafka简单消费->topic:" + record.topic()
                + ",key:" + record.key()
                + ",value:" + record.value()
                + ",time:" + record.timestamp());
    }

    @KafkaListener(groupId = GroupConstant.SPRING_KAFKA_CONSUMER_GROUP_02, topicPartitions = {
            @TopicPartition(topic = TopicConstant.SPRING_KAFKA_TOPIC_CHAP_01, partitions = {"1"}),
            @TopicPartition(topic = TopicConstant.SPRING_KAFKA_TOPIC_SPECIFIED_CONSUMER, partitions = {"0"})
    })
    public void onMessageSpecified(ConsumerRecord<String, Object> record) {
        System.err.println("kafka指定主题|分区|offset消费·->topic:" + record.topic()
                + "partition:" + record.partition()
                + ",key:" + record.key()
                + ",value:" + record.value()
                + ",time:" + record.timestamp());
    }
}
