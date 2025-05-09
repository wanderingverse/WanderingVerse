package com.wanderingverse.mapper.blogmapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.BlogPostPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * 博客文章表
 *
 * @author lihui
 * @date 2025/05/09 15:14
 */
@Mapper
public interface BlogPostMapper extends MPJBaseMapper<BlogPostPO> {
    /**
     * 清空表数据
     */
    @Update("truncate table blog_post")
    void truncate();
}