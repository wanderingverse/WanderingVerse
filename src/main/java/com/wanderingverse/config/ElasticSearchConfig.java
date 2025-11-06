package com.wanderingverse.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.wanderingverse.common.SystemCommon.HASHMAP_INITIAL_CAPACITY;


/**
 * @author WanderingVerse
 * @since 2025/09/25 23:59
 */
@Slf4j
@Configuration
public class ElasticSearchConfig {

    @Value("${spring.elasticsearch.uris}")
    private String host;

    private RestHighLevelClient elasticSearchClient;


    @PostConstruct
    private void initElasticSearchClient() {
        RestClientBuilder builder = RestClient.builder(HttpHost.create(host));
        elasticSearchClient = new RestHighLevelClient(builder);
    }

    @PreDestroy
    private void destroyElasticSearchClient() throws IOException {
        if (!ObjectUtils.isEmpty(elasticSearchClient)) {
            elasticSearchClient.close();
        }
    }

    /**
     * 创建 ElasticSearch 索引库
     */
    public CreateIndexResponse createIndex(String indexName, Class<?> clazz) {
        CreateIndexResponse response = null;
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.mapping(buildElasticSearchMapping(clazz));
        try {
            response = elasticSearchClient.indices().create(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("ElasticSearch 索引创建失败：", e);
        }
        return response;
    }

    /**
     * 删除 ElasticSearch 索引库
     */
    public AcknowledgedResponse deleteIndex(String indexName) {
        AcknowledgedResponse response = null;
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        try {
            response = elasticSearchClient.indices().delete(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("ElasticSearch 索引删除失败：", e);
        }
        return response;
    }

    /**
     * 判断索引库是否存在
     */
    public boolean existsIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        boolean exists = false;
        try {
            exists = elasticSearchClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("ElasticSearch 索引查询失败：", e);
        }
        return exists;
    }


    /**
     * 新增文档
     * <p>文档 id 相同时将覆盖
     *
     * @param indexName 索引名称
     * @param id        文档 id
     * @param document  文档对象
     * @return IndexResponse
     */
    public IndexResponse insertDocument(String indexName, String id, Object document) {
        IndexResponse indexResponse = null;
        IndexRequest request = new IndexRequest(indexName).id(id);
        Map<String, Object> documentMap = new ObjectMapper().convertValue(document, new TypeReference<>() {
        });
        request.source(documentMap);
        try {
            indexResponse = elasticSearchClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("ElasticSearch 文档插入失败：", e);
        }
        return indexResponse;
    }

    /**
     * 根据文档 id 查询文档
     */
    public <T> T queryDocumentById(String indexName, String id, Class<T> clazz) {
        T document = null;
        GetRequest request = new GetRequest(indexName, id);
        try {
            GetResponse response = elasticSearchClient.get(request, RequestOptions.DEFAULT);
            Map<String, Object> sourceMap = response.getSourceAsMap();
            document = new ObjectMapper().convertValue(sourceMap, clazz);
        } catch (Exception e) {
            log.error("ElasticSearch 文档查询失败：", e);
        }
        return document;
    }

    /**
     * 根据文档 id 删除文档
     */
    public DeleteResponse deleteDocumentById(String indexName, String id) {
        DeleteResponse deleteResponse = null;
        DeleteRequest request = new DeleteRequest(indexName, id);
        try {
            deleteResponse = elasticSearchClient.delete(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("ElasticSearch 文档删除失败：", e);
        }
        return deleteResponse;
    }


    /**
     * 根据实体类构建 ElasticSearch 索引映射
     *
     * @param clazz 实体类
     * @return 索引映射
     */
    public Map<String, Object> buildElasticSearchMapping(Class<?> clazz) {
        Map<String, Object> properties = new HashMap<>(HASHMAP_INITIAL_CAPACITY);
        for (Field field : clazz.getDeclaredFields()) {
            Map<String, Object> fieldMapping = new HashMap<>(HASHMAP_INITIAL_CAPACITY);
            Class<?> fieldType = field.getType();
            if (fieldType.equals(String.class)) {
                fieldMapping.put("type", "text");
            } else if (fieldType.equals(Integer.class) || fieldType.equals(Long.class)) {
                fieldMapping.put("type", "long");
            } else if (fieldType.equals(Double.class) || fieldType.equals(Float.class)) {
                fieldMapping.put("type", "double");
            } else if (fieldType.equals(Date.class)) {
                fieldMapping.put("type", "date");
            }
            properties.put(field.getName(), fieldMapping);
        }
        Map<String, Object> mapping = new HashMap<>(HASHMAP_INITIAL_CAPACITY);
        mapping.put("properties", properties);
        return mapping;
    }
}
