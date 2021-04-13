package com.ethen.es;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.*;

public class EsService {
    public static RestHighLevelClient client;
    public static final String INDEX_FORUM_MYSQL = "forum-mysql";
    public static final String TYPE_FORUM_MYSQL = "doc";

    static {
        System.setProperty("log4j.configurationFile","classpath:log4j2.xml");
        String[] ips = {"localhost:9200"};
        HttpHost[] httpHosts = new HttpHost[ips.length];
        for (int i = 0; i < ips.length; i++) {
            httpHosts[i] = HttpHost.create(ips[i]);
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
        client = new RestHighLevelClient(builder.build());
    }

    public static void main(String[] args) throws IOException {
        // 添加索引
        InputStream input = new FileInputStream("D:\\idea2021\\book-elastic-search-in-action-resources\\3.6_data_migration\\forum-mysql-dump.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            JSONObject lineJson = JSONObject.parseObject(line);
            JSONObject source = lineJson.getJSONObject("_source");
            System.err.println(source);
            IndexRequest request = new IndexRequest(INDEX_FORUM_MYSQL, TYPE_FORUM_MYSQL, lineJson.getString("_id"));
            request.source(source);
            IndexResponse response = client.index(request);
            System.err.println(response);
//            client.bulk() 批处理操作
        }
    }
}
