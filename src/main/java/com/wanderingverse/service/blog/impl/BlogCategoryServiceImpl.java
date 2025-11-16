package com.wanderingverse.service.blog.impl;

import com.wanderingverse.mapper.blog.BlogCategoryMapper;
import com.wanderingverse.mapper.blog.CategoryPostMapper;
import com.wanderingverse.model.entity.BlogCategoryDO;
import com.wanderingverse.model.entity.CategoryPostDO;
import com.wanderingverse.service.blog.BlogCategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author lihui
 * @since 2025/11/11 13:52
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Resource
    private BlogCategoryMapper blogCategoryMapper;
    @Resource
    private CategoryPostMapper categoryPostMapper;

    @Override
    public boolean addBlogCategory(BlogCategoryDO blogCategory) {
        BlogCategoryDO blogCategoryForInsert = new BlogCategoryDO()
                .setCategoryName(blogCategory.getCategoryName())
                .setSortOrder(blogCategory.getSortOrder());
        return blogCategoryMapper.insert(blogCategoryForInsert) > 0;
    }

    @Override
    public List<BlogCategoryDO> getBlogCategoryList() {
        return blogCategoryMapper.selectList(null);
    }

    @Override
    public boolean deleteBlogCategory(String id) {
        return blogCategoryMapper.deleteById(id) > 0;
    }

    @Override
    public boolean bindCategoryAndPost(List<String> blogCategoryIdList, String blogPostId) {
        if (CollectionUtils.isEmpty(blogCategoryIdList) || blogPostId == null) {
            return false;
        }
        List<CategoryPostDO> categoryPostList = blogCategoryIdList.stream().map(blogCategoryId -> new CategoryPostDO()
                .setBlogCategoryId(blogCategoryId)
                .setBlogPostId(blogPostId)).toList();
        categoryPostMapper.insert(categoryPostList);
        return true;
    }
}