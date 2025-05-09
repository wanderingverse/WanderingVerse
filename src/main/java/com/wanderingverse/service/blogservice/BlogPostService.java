package com.wanderingverse.service.blogservice;

import com.wanderingverse.model.entity.BlogPostPO;

/**
 * 博客文章 Service
 * @author lihui
 * @date 2025/05/09 13:25
 **/
public interface BlogPostService {

    /**
     * 添加博客文章
     * @param blogPost 博客文章对象
     * @return boolean
     */
    boolean addBlogPost(BlogPostPO blogPost);
}
