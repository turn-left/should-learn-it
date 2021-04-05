package com.ethen.kafka.spring.vo;

import lombok.Data;

@Data
public class MessageVO {
    private String topic;
    private String key;
    private int partition;
    private Object value;
}
