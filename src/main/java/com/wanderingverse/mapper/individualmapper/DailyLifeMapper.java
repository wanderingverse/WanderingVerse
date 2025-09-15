package com.wanderingverse.mapper.individualmapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.DailyLifeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * @author lihui
 * @since 2025/9/12 17:51
 */
@Mapper
public interface DailyLifeMapper extends MPJBaseMapper<DailyLifeDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table daily_life")
    void truncate();
}