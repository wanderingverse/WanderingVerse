package com.wanderingverse.controller.blog;

import com.wanderingverse.model.entity.BlogCategoryDO;
import com.wanderingverse.service.blog.BlogCategoryService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lihui
 * @since 2025/11/11 13:53
 */
@Validated
@RestController
@RequestMapping("/blog_category")
public class BlogCategoryController {

    @Resource
    private BlogCategoryService blogCategoryService;

    /**
     * 添加博客分类
     */
    @PostMapping("/")
    public AjaxResult addBlogCategory(@RequestBody @Valid BlogCategoryDO blogCategory) {
        boolean result = blogCategoryService.addBlogCategory(blogCategory);
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询博客分类列表
     */
    @GetMapping("/list")
    public AjaxResult getBlogCategoryList() {
        List<BlogCategoryDO> blogCategoryList = blogCategoryService.getBlogCategoryList();
        return AjaxResult.success(blogCategoryList);
    }

    /**
     * 删除博客分类
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteBlogCategory(@PathVariable String id) {
        boolean result = blogCategoryService.deleteBlogCategory(id);
        return result ? AjaxResult.success() : AjaxResult.error();
    }
}