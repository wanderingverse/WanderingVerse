package com.wanderingverse.service.individual;

import com.wanderingverse.model.bo.TreeNode;
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
     * @param id   节点 id
     * @param mode 列表结构。1：树结构，2：扁平列表结构
     * @return List<TreeNode < LivingStateDO>>
     */
    List<TreeNode<LivingStateDO>> getLivingStateList(String id, Integer mode);


    /**
     * 获取生活状态详情
     *
     * @param id LivingStateDO.id
     * @return LivingStateDO
     */
    LivingStateDO getLivingStateDetail(Long id);
}
