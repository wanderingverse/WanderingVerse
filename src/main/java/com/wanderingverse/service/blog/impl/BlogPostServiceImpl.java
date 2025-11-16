package com.wanderingverse.service.blog.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.mapper.blog.BlogPostContentMapper;
import com.wanderingverse.mapper.blog.BlogPostMapper;
import com.wanderingverse.mapper.blog.CategoryPostMapper;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.model.dto.response.BlogPostResponseDTO;
import com.wanderingverse.model.entity.BlogPostContentDO;
import com.wanderingverse.model.entity.BlogPostDO;
import com.wanderingverse.model.entity.CategoryPostDO;
import com.wanderingverse.service.blog.BlogCategoryService;
import com.wanderingverse.service.blog.BlogPostService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author lihui
 * @since 2025/8/15 16:18
 */
@Service
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {
    @Resource
    private BlogPostMapper blogPostMapper;
    @Resource
    private BlogPostContentMapper blogPostContentMapper;
    @Resource
    private CategoryPostMapper categoryPostMapper;
    @Resource
    private MinioConfig minioConfig;

    @Resource
    @Lazy
    private BlogCategoryService blogCategoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBlogPost(BlogPostRequestDTO blogPostRequest) {
        if (ObjectUtils.isEmpty(blogPostRequest)) {
            return false;
        }
        if (CollectionUtils.isEmpty(blogPostRequest.getBlogCategoryIdList())) {
            return false;
        }
        // 写入博客正文
        BlogPostContentDO blogPostContent = new BlogPostContentDO();
        blogPostContent.setContent(blogPostRequest.getContent());
        blogPostContentMapper.insert(blogPostContent);
        // 写入博客内容
        BlogPostDO blogPost = new BlogPostDO();
        blogPost.setTitle(blogPostRequest.getTitle());
        blogPost.setSummary(blogPostRequest.getSummary());
        // todo 未来是登录后获取用户 id
        blogPost.setAuthorId("1");
        blogPost.setContentId(blogPostContent.getId());
        blogPost.setDeleteStatus((byte) 0);
        if (blogPost.getSummary().isBlank()) {
            // todo 未来是 AI 生成摘要
            blogPost.setSummary("未来是AI生成摘要");
        }
        if (!ObjectUtils.isEmpty(blogPostRequest.getCreateTime())) {
            blogPost.setCreateTime(blogPost.getCreateTime());
        }
        int blogPostInsertResult = blogPostMapper.insert(blogPost);
        // 写入博客分类
        boolean result = blogCategoryService.bindCategoryAndPost(blogPostRequest.getBlogCategoryIdList(), blogPost.getId());
        return true;
    }

    @Override
    public IPage<BlogPostResponseDTO> getBlogPostList(Long pageNum, Long pageSize, String blogCategoryId, LocalDateTime createStartTime, LocalDateTime createEndTime) {
        MPJLambdaWrapper<BlogPostDO> blogPostQueryWrapper = new MPJLambdaWrapper<BlogPostDO>()
                .selectAll(BlogPostDO.class)
                .leftJoin(CategoryPostDO.class, CategoryPostDO::getBlogPostId, BlogPostDO::getId)
                .eq(blogCategoryId != null, CategoryPostDO::getBlogCategoryId, blogCategoryId)
                .ge(createStartTime != null, BlogPostDO::getCreateTime, createStartTime)
                .le(createEndTime != null, BlogPostDO::getUpdateTime, createEndTime)
                .leftJoin(BlogPostContentDO.class, BlogPostContentDO::getId, BlogPostDO::getContentId)
                .select(BlogPostContentDO::getContent)
                .groupBy(BlogPostDO::getId)
                .orderByDesc(BlogPostDO::getCreateTime);
        IPage<BlogPostResponseDTO> blogPostPage = blogPostMapper.selectJoinPage(new Page<>(pageNum, pageSize), BlogPostResponseDTO.class, blogPostQueryWrapper);
        for (BlogPostResponseDTO blogPostResponseDTO : blogPostPage.getRecords()) {
            // 标记文章拥有的标签
            MPJLambdaWrapper<CategoryPostDO> categoryPostQueryWrapper = new MPJLambdaWrapper<CategoryPostDO>()
                    .select(CategoryPostDO::getBlogCategoryId)
                    .eq(CategoryPostDO::getBlogPostId, blogPostResponseDTO.getId());
            List<String> blogCategoryIdList = categoryPostMapper.selectJoinList(String.class, categoryPostQueryWrapper);
            blogPostResponseDTO.setBlogCategoryIdList(blogCategoryIdList);
            // todo 估算阅读时间，算法或者分词器
            blogPostResponseDTO.setReadingTimeInSeconds("184");
        }
        return blogPostPage;
    }

    @Override
    public BlogPostResponseDTO getBlogPostDetail(String id) {
        MPJLambdaWrapper<BlogPostDO> blogPostQueryWrapper = new MPJLambdaWrapper<BlogPostDO>()
                .selectAll(BlogPostDO.class)
                .leftJoin(BlogPostContentDO.class, BlogPostContentDO::getId, BlogPostDO::getContentId)
                .select(BlogPostContentDO::getContent)
                .eq(BlogPostDO::getId, id);
        return blogPostMapper.selectJoinOne(BlogPostResponseDTO.class, blogPostQueryWrapper);
    }
}