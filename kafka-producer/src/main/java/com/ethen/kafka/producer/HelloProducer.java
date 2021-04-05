package com.ethen.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

/**
 * kafka producer in action
 *
 * @see <a href="https://ropledata.blog.csdn.net/article/details/105682276">
 * kafka实战篇（一）：Producer消息发送实战</a>
 */
public class HelloProducer {
    public static final String TURN_LEFT_TOPIC = "turn.left.topic01";
    public static final Random RANDOM = new SecureRandom();

    public static void main(String[] args) {
        // 生产者配置
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);// 批次大小
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // 等待时间
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // 缓冲区 RecordAccumulator大小
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 构造生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        // 发送消息
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TURN_LEFT_TOPIC, "test-key-" + 5*i, "test-value-" + RANDOM.nextLong());
            System.out.println("HelloProducer record:" + record);
            producer.send(record);
        }

    }
}
