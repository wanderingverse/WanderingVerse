package com.wanderingverse.mapper.blog;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.BlogCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * @author lihui
 * @since 2025/11/11 13:50
 */
@Mapper
public interface BlogCategoryMapper extends MPJBaseMapper<BlogCategoryDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table blog_category")
    void truncate();
}