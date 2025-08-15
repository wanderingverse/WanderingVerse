package com.wanderingverse.service.blogservice.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.wanderingverse.mapper.blogmapper.BlogPostContentMapper;
import com.wanderingverse.mapper.blogmapper.BlogPostMapper;
import com.wanderingverse.model.dto.request.BlogPostRequestDTO;
import com.wanderingverse.model.dto.response.BlogPostResponseDTO;
import com.wanderingverse.model.entity.BlogPostContentDO;
import com.wanderingverse.model.entity.BlogPostDO;
import com.wanderingverse.service.blogservice.BlogPostService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addBlogPost(BlogPostRequestDTO blogPostRequest) {
        if (ObjectUtils.isEmpty(blogPostRequest)) {
            return false;
        }
        BlogPostContentDO blogPostContent = new BlogPostContentDO();
        blogPostContent.setContent(blogPostRequest.getContent());
        blogPostContentMapper.insert(blogPostContent);

        BlogPostDO blogPost = new BlogPostDO();
        blogPost.setTitle(blogPostRequest.getTitle());
        blogPost.setSummary(blogPostRequest.getSummary());
        // todo 未来是登录后获取用户 id
        blogPost.setAuthorId("1");
        blogPost.setContentId(blogPostContent.getId());
        blogPost.setDeleteStatus((byte) 0);
        if (blogPost.getSummary().isBlank()) {
            // todo 未来是AI生成摘要
            blogPost.setSummary("未来是AI生成摘要");
        }
        if (!ObjectUtils.isEmpty(blogPostRequest.getCreateTime())) {
            blogPost.setCreateTime(blogPost.getCreateTime());
        }
        return blogPostMapper.insert(blogPost) > 0;
    }

    @Override
    public IPage<BlogPostResponseDTO> getBlogPostList(Integer pageNum, Integer pageSize) {
        MPJLambdaWrapper<BlogPostDO> blogPostQueryWrapper = new MPJLambdaWrapper<BlogPostDO>()
                .selectAll(BlogPostDO.class)
                .innerJoin(BlogPostContentDO.class, BlogPostContentDO::getId, BlogPostDO::getContentId)
                .select(BlogPostContentDO::getContent);
        IPage<BlogPostResponseDTO> blogPostPage = blogPostMapper.selectJoinPage(new Page<>(pageNum, pageSize), BlogPostResponseDTO.class, blogPostQueryWrapper);
        if (ObjectUtils.isEmpty(blogPostPage)) {
            return new Page<>();
        }
        return blogPostPage;
    }

    @Override
    public BlogPostResponseDTO getBlogPostDetail(Long id) {
        MPJLambdaWrapper<BlogPostDO> blogPostQueryWrapper = new MPJLambdaWrapper<BlogPostDO>()
                .selectAll(BlogPostDO.class)
                .innerJoin(BlogPostContentDO.class, BlogPostContentDO::getId, BlogPostDO::getContentId)
                .select(BlogPostContentDO::getContent)
                .eq(BlogPostDO::getId, id);
        BlogPostResponseDTO blogPostPage = blogPostMapper.selectJoinOne(BlogPostResponseDTO.class, blogPostQueryWrapper);
        if (ObjectUtils.isEmpty(blogPostPage)) {
            return new BlogPostResponseDTO();
        }
        return blogPostPage;
    }
}
