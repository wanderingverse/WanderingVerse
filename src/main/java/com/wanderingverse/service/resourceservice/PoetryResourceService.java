package com.wanderingverse.service.resourceservice;

import com.wanderingverse.model.entity.PoetryDO;

/**
 * @author lihui
 * @since 2025/10/27 12:11
 */
public interface PoetryResourceService {

    /**
     * 随机获取一首诗词
     *
     * @return PoetryDO
     */
    PoetryDO getRandomPoetry();

    /**
     * 异步保存一首诗词
     *
     * @param poetry PoetryDO
     */
    void saveAsync(PoetryDO poetry);
}