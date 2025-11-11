package com.wanderingverse.controller.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.model.dto.response.BlogPostResponseDTO;
import com.wanderingverse.service.blog.BlogPostService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 博客文章 Controller
 *
 * @author lihui
 * @since 2025/8/15 15:24
 */
@Slf4j
@RestController
@RequestMapping("/blog/post")
public class BlogPostController {
    @Resource
    private BlogPostService blogPostService;

    /**
     * 获取博客文章列表
     */
    @GetMapping("/list")
    public AjaxResult getBlogPostList(@RequestParam(defaultValue = "1") Long pageNum,
                                      @RequestParam(defaultValue = Long.MAX_VALUE + "") Long pageSize,
                                      @RequestParam(required = false) Long blogCategoryId) {
        IPage<BlogPostResponseDTO> blogPostPage = blogPostService.getBlogPostList(pageNum, pageSize, blogCategoryId);
        return AjaxResult.success(blogPostPage);
    }

    /**
     * 添加博客文章
     */
    @PostMapping("/add")
    public AjaxResult addBlogPost(@RequestBody @Valid BlogPostRequestDTO blogPost) {
        boolean result = blogPostService.addBlogPost(blogPost);
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 获取博客文章详情
     */
    @GetMapping("/detail")
    public AjaxResult getBlogPostDetail(@RequestParam Long id) {
        BlogPostResponseDTO blogPost = blogPostService.getBlogPostDetail(id);
        return AjaxResult.success(blogPost);
    }
}
