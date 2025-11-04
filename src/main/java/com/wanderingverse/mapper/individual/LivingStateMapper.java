package com.wanderingverse.mapper.individual;

import com.github.yulichang.base.MPJBaseMapper;
import com.wanderingverse.model.entity.LivingStateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


/**
 * @author lihui
 * @date 2025/5/13 0:22
 **/
@Mapper
public interface LivingStateMapper extends MPJBaseMapper<LivingStateDO> {
    /**
     * 清空表数据
     */
    @Update("truncate table living_state")
    void truncate();
}