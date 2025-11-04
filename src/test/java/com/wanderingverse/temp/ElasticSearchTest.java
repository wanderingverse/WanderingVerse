package com.wanderingverse.temp;


import com.wanderingverse.config.ElasticSearchConfig;
import com.wanderingverse.mapper.blog.BlogPostContentMapper;
import com.wanderingverse.model.entity.BlogPostContentDO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@Slf4j
@SpringBootTest
class ElasticSearchTest {
    @Resource
    private ElasticSearchConfig elasticSearchConfig;
    @Resource
    private BlogPostContentMapper blogPostContentMapper;

    @Test
    void createIndexTest() {
        List<BlogPostContentDO> blogPostContentDOList = blogPostContentMapper.selectList(null);
        for (BlogPostContentDO blogPostContentDO : blogPostContentDOList) {
            elasticSearchConfig.insertDocument("blog_post_content", blogPostContentDO.getId(), blogPostContentDO);
        }
        BlogPostContentDO blogPostContent = elasticSearchConfig.queryDocumentById("blog_post_content", "1", BlogPostContentDO.class);
        log.info("查询结果：{}", blogPostContent);
    }
}