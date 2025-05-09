package com.wanderingverse.controller.blogcontroller;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.service.blogservice.BlogPostService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 博客文章 Controller
 *
 * @author lihui
 * @date 2025/05/09 11:48
 **/
@RestController
@RequestMapping("/blog/post")
@Slf4j
public class BlogPostController {
    @Resource
    private BlogPostService blogPostService;

    /**
     * 查询博客文章列表
     */
    @GetMapping("/list")
    public AjaxResult getBlogPostList() {
        return null;
    }

    /**
     * 添加博客文章
     */
    @PostMapping("/add")
    public AjaxResult addBlogPost(@RequestBody BlogPostRequestDTO blogPost) {
        boolean result = blogPostService.addBlogPost(blogPost);
        if (!result) {
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }
}
