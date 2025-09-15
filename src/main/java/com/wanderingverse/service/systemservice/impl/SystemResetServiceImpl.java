package com.wanderingverse.service.systemservice.impl;

import com.wanderingverse.mapper.blogmapper.BlogPostContentMapper;
import com.wanderingverse.mapper.blogmapper.BlogPostMapper;
import com.wanderingverse.mapper.individualmapper.DailyLifeMapper;
import com.wanderingverse.mapper.individualmapper.LivingStateMapper;
import com.wanderingverse.mapper.systemmapper.UserMapper;
import com.wanderingverse.service.systemservice.SystemResetService;
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
