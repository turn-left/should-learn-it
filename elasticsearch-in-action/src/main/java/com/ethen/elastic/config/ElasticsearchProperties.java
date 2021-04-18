package com.ethen.elastic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {
    private String host;
    private int port;
    private int connTimeout;
    private int socketTimeout;
    private int connectionRequestTimeout;
}
