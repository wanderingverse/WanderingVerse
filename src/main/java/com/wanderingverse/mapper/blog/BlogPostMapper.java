package com.wanderingverse.mapper.blog;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.BlogPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * @author lihui
 * @date 2025/05/09 15:14
 */
@Mapper
public interface BlogPostMapper extends MPJBaseMapper<BlogPostDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table blog_post")
    void truncate();
}