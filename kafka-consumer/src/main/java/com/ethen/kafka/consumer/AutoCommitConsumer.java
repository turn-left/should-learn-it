package com.ethen.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class AutoCommitConsumer {
    public static final String TURN_LEFT_TOPIC = "turn.left.topic01";
    public static final String SPRING_KAFKA_TOPIC_CHAP_01 = "spring-kafka-topic-chap01";


    public static void main(String[] args) {
        // kafka消费者客户端属性配置
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // kafka集群，逗号分隔
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "auto-commit-consumer-group-01");// 消费者组
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());// 反序列化器
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());// 反序列化器
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");// 自动提交
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");// 自动提交间隔
        // 创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅topic
        consumer.subscribe(Collections.singleton(SPRING_KAFKA_TOPIC_CHAP_01));
        // 消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000L));
            for (ConsumerRecord<String, String> record : records) {
                System.err.printf(
                        "partition:%s,topic:%s,offset%s,key:%s,value:%s%n",
                        record.partition(), record.topic(), record.offset(),
                        record.key(), record.value());
            }
        }
    }
}
