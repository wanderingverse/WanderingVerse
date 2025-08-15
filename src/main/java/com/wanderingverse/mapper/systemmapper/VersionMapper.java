package com.wanderingverse.mapper.systemmapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author lihui
 * @since 2025/8/15 15:20
 */
@Mapper
public interface VersionMapper {
    /**
     * 获取系统使用数据库版本
     *
     * @return 数据库版本
     */
    @Select("SELECT VERSION()")
    String getDataBaseVersion();
}
