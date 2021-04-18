package com.ethen.elastic.service.impl;

import com.ethen.elastic.service.UserService;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestHighLevelClient esClient;

    @Override
    public Object createUserIndex(String indexName) {
        CreateIndexRequest createRequest = new CreateIndexRequest(indexName);
        createRequest.settings(Settings.builder()
                .put("index_number_of_shards", 1)
                .put("index_number_of_replicas", 0)
        );
        createRequest.mapping("{\n" +
                "  \"properties\": {\n" +
                "    \"city\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"sex\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    }\n" +
                "  }\n" +
                "}", XContentType.JSON);
        try {
            CreateIndexResponse response = esClient.indices().create(createRequest, RequestOptions.DEFAULT);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
