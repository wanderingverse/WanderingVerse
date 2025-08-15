package com.wanderingverse.service.individualservice;

import com.wanderingverse.model.entity.LivingStateDO;

import java.util.List;


/**
 * @author lihui
 * @since 2025/8/15 16:16
 */
public interface LivingStateService {

    /**
     * 添加生活状态
     *
     * @param livingState 生活状态实体类
     * @return boolean
     */
    boolean addLivingState(LivingStateDO livingState);


    /**
     * 获取生活状态列表
     *
     * @return List<LivingStateDO>
     */
    List<LivingStateDO> getLivingStateList();
}
