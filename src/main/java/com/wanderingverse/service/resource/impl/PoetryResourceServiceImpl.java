package com.wanderingverse.service.resource.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderingverse.mapper.resource.PoetryMapper;
import com.wanderingverse.model.entity.PoetryDO;
import com.wanderingverse.service.resource.PoetryResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * @author lihui
 * @since 2025/10/27 12:11
 */
@Slf4j
@Service
public class PoetryResourceServiceImpl implements PoetryResourceService {

    @Resource
    private PoetryMapper poetryMapper;

    @Override
    public PoetryDO getRandomPoetry() {
        PoetryDO poetry = new PoetryDO();
        Long count = poetryMapper.selectCount(null);
        int offset = 10;
        long randomIndex = (long) (Math.random() * count);
        LambdaQueryWrapper<PoetryDO> poetryLambdaQueryWrapper = new LambdaQueryWrapper<PoetryDO>()
                .le(PoetryDO::getId, randomIndex + offset)
                .ge(PoetryDO::getId, randomIndex - offset);
        PoetryDO poetryOfRandom = poetryMapper.selectOne(poetryLambdaQueryWrapper, false);
        if (!ObjectUtils.isEmpty(poetryOfRandom)) {
            poetry = poetryOfRandom;
        }
        return poetry;
    }

    @Async
    @Override
    public void saveAsync(PoetryDO poetry) {
        if (ObjectUtils.isEmpty(poetry)) {
            return;
        }
        PoetryDO poetryForInsert = new PoetryDO()
                .setTitle(poetry.getTitle())
                .setAuthor(poetry.getAuthor())
                .setContent(poetry.getContent())
                .setSha256(poetry.getSha256())
                .setCategory(poetry.getCategory());
        poetryMapper.insert(poetryForInsert);
    }
}