package com.wanderingverse.mapper.blog;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.BlogPostContentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * @author lihui
 * @date 2025/05/14 12:38
 */
@Mapper
public interface BlogPostContentMapper extends MPJBaseMapper<BlogPostContentDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table blog_post_content")
    void truncate();
}