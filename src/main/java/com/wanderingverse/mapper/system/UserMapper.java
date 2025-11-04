package com.wanderingverse.mapper.system;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author lihui
 * @since 2025/05/09 15:37
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<UserDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table user")
    void truncate();
}