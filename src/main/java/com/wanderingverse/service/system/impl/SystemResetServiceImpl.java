package com.wanderingverse.service.system.impl;

import com.wanderingverse.mapper.blog.BlogCategoryMapper;
import com.wanderingverse.mapper.blog.BlogPostContentMapper;
import com.wanderingverse.mapper.blog.BlogPostMapper;
import com.wanderingverse.mapper.blog.CategoryPostMapper;
import com.wanderingverse.mapper.individual.DailyLifeMapper;
import com.wanderingverse.mapper.individual.LivingStateMapper;
import com.wanderingverse.mapper.resource.PoetryMapper;
import com.wanderingverse.mapper.system.UserMapper;
import com.wanderingverse.service.system.SystemResetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * @author lihui
 * @since 2025/8/15 16:15
 */
@Service
public class SystemResetServiceImpl implements SystemResetService {
    @Resource
    private BlogCategoryMapper blogCategoryMapper;
    @Resource
    private BlogPostMapper blogPostMapper;
    @Resource
    private BlogPostContentMapper blogPostContentMapper;
    @Resource
    private CategoryPostMapper categoryPostMapper;
    @Resource
    private DailyLifeMapper dailyLifeMapper;
    @Resource
    private LivingStateMapper livingStateMapper;
    @Resource
    private PoetryMapper poetryMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void resetDatabase() {
        blogCategoryMapper.truncate();
        blogPostMapper.truncate();
        blogPostContentMapper.truncate();
        categoryPostMapper.truncate();
        dailyLifeMapper.truncate();
        livingStateMapper.truncate();
        poetryMapper.truncate();
        userMapper.truncate();
    }
}
