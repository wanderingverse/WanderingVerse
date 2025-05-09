package com.wanderingverse.service.blogservice.impl;

import com.wanderingverse.mapper.blogmapper.BlogPostMapper;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.service.blogservice.BlogPostService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lihui
 * @date 2025/05/09 13:25
 **/
@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {
    @Resource
    private BlogPostMapper blogPostMapper;

    @Override
    public boolean addBlogPost(BlogPostRequestDTO blogPost) {
//        blogPostMapper.insert(blogPost);
        return true;
    }
}
