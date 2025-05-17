package com.wanderingverse.service.systemservice.impl;

import com.wanderingverse.mapper.blogmapper.BlogPostContentMapper;
import com.wanderingverse.mapper.blogmapper.BlogPostMapper;
import com.wanderingverse.mapper.individualmapper.LivingStateMapper;
import com.wanderingverse.mapper.systemmapper.UserMapper;
import com.wanderingverse.service.systemservice.SystemResetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lihui
 * @date 2025/05/09 15:18
 **/
@Service
public class SystemResetServiceImpl implements SystemResetService {
    @Resource
    private BlogPostMapper blogPostMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private LivingStateMapper livingStateMapper;
    @Resource
    private BlogPostContentMapper blogPostContentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetDatabase() {
        userMapper.truncate();
        blogPostMapper.truncate();
        livingStateMapper.truncate();
        blogPostContentMapper.truncate();
        return true;
    }
}
