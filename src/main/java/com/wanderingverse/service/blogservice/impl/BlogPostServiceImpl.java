package com.wanderingverse.service.blogservice.impl;

import com.wanderingverse.model.entity.BlogPostPO;
import com.wanderingverse.service.blogservice.BlogPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihui
 * @date 2025/05/09 13:25
 **/
@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {
    @Override
    public boolean addBlogPost(BlogPostPO blogPost) {
        return false;
    }
}
