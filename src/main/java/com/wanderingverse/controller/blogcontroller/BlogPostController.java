package com.wanderingverse.controller.blogcontroller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.model.dto.response.BlogPostResponseDTO;
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
     * 获取博客文章列表
     */
    @GetMapping("/list")
    public AjaxResult getBlogPostList(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = Integer.MAX_VALUE + "") Integer pageSize) {
        IPage<BlogPostResponseDTO> blogPostPage = blogPostService.getBlogPostList(pageNum, pageSize);
        return AjaxResult.success("查询成功", blogPostPage);
    }

    /**
     * 添加博客文章
     */
    @PostMapping("/add")
    public AjaxResult addBlogPost(@RequestBody BlogPostRequestDTO blogPost) {
        boolean result = blogPostService.addBlogPost(blogPost);
        return result ? AjaxResult.success("添加成功") : AjaxResult.error("添加失败");
    }

    /**
     * 获取博客文章详情
     */
    @GetMapping("/detail")
    public AjaxResult getBlogPostDetail(@RequestParam Long id) {
        BlogPostResponseDTO blogPost = blogPostService.getBlogPostDetail(id);
        return AjaxResult.success("查询成功", blogPost);
    }
}
