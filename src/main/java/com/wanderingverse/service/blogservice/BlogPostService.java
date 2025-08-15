package com.wanderingverse.service.blogservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.model.dto.response.BlogPostResponseDTO;


/**
 * 博客文章 Service
 *
 * @author lihui
 * @since 2025/8/15 16:17
 */
public interface BlogPostService {

    /**
     * 添加博客文章
     *
     * @param blogPost 博客文章对象
     * @return boolean
     */
    boolean addBlogPost(BlogPostRequestDTO blogPost);

    /**
     * 获取博客文章列表
     *
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     * @return IPage<BlogPostResponseDTO>
     */
    IPage<BlogPostResponseDTO> getBlogPostList(Integer pageNum, Integer pageSize);

    /**
     * 获取博客文章详情
     *
     * @param id 博客 id
     * @return BlogPostResponseDTO
     */
    BlogPostResponseDTO getBlogPostDetail(Long id);
}
