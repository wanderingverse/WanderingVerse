package com.wanderingverse.service.system.impl;

import com.wanderingverse.mapper.blog.BlogPostContentMapper;
import com.wanderingverse.mapper.blog.BlogPostMapper;
import com.wanderingverse.mapper.individual.DailyLifeMapper;
import com.wanderingverse.mapper.individual.LivingStateMapper;
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
    private BlogPostMapper blogPostMapper;
    @Resource
    private BlogPostContentMapper blogPostContentMapper;
    @Resource
    private LivingStateMapper livingStateMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DailyLifeMapper dailyLifeMapper;

    @Override
    public boolean resetDatabase() {
        userMapper.truncate();
        blogPostMapper.truncate();
        livingStateMapper.truncate();
        blogPostContentMapper.truncate();
        dailyLifeMapper.truncate();
        return true;
    }
}
