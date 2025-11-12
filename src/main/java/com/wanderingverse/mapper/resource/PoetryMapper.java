package com.wanderingverse.mapper.resource;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.PoetryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author lihui
 * @since 2025/10/27 12:09
 */
@Mapper
public interface PoetryMapper extends MPJBaseMapper<PoetryDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table poetry")
    void truncate();
}