package com.ethen.kafka.spring.config;

import com.ethen.kafka.spring.constant.TopicConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see => https://blog.csdn.net/yuanlong122716/article/details/105160545/
 */
@Configuration
public class KafkaConfig {
    /**
     * 分区数
     */
    public static final int PARTITION_NUM = 2;
    /**
     * 分区副本数
     */
    public static final short REPLICA_NUM = 1;

    /**
     * 初始化主题
     *
     * @return topic
     */
    @Bean
    public NewTopic newTopic() {
        return new NewTopic(TopicConstant.SPRING_KAFKA_TOPIC_CHAP_01, PARTITION_NUM, REPLICA_NUM);
    }

    /**
     * 另外一个topic
     *
     * @return topic02
     */
    @Bean
    public NewTopic newTopic02() {
        return new NewTopic(TopicConstant.SPRING_KAFKA_TOPIC_SPECIFIED_CONSUMER, PARTITION_NUM, REPLICA_NUM);
    }
}
