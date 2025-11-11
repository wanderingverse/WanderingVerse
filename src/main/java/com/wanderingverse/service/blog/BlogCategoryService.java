package com.wanderingverse.service.blog;


import com.wanderingverse.model.entity.BlogCategoryDO;

import java.util.List;

/**
 * @author lihui
 * @since 2025/11/11 13:52
 */
public interface BlogCategoryService {

    /**
     * 添加博客分类
     *
     * @param blogCategory BlogCategoryDO
     * @return boolean
     */
    boolean addBlogCategory(BlogCategoryDO blogCategory);

    /**
     * 获取博客分类列表
     *
     * @return List<BlogCategoryDO>
     */
    List<BlogCategoryDO> getBlogCategoryList();

    /**
     * 删除博客分类
     *
     * @param id BlogCategoryDO.id
     * @return boolean
     */
    boolean deleteBlogCategory(Long id);

    /**
     * 绑定博客分类和文章
     *
     * @param blogCategoryIdList BlogCategoryDO.id
     * @param blogPostId         BlogPostDO.id
     * @return boolean
     */
    boolean bindCategoryAndPost(List<Long> blogCategoryIdList, Long blogPostId);
}