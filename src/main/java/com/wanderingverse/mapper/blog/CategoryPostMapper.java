package com.wanderingverse.mapper.blog;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.CategoryPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * @author lihui
 * @since 2025/11/11 13:50
 */
@Mapper
public interface CategoryPostMapper extends MPJBaseMapper<CategoryPostDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table category_post")
    void truncate();
}