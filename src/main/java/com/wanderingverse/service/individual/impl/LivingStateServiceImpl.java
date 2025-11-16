package com.wanderingverse.service.individual.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderingverse.mapper.individual.LivingStateMapper;
import com.wanderingverse.model.bo.TreeNode;
import com.wanderingverse.model.entity.LivingStateDO;
import com.wanderingverse.service.individual.LivingStateService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wanderingverse.util.TreeStructureUtils.*;

/**
 * @author lihui
 * @since 2025/8/15 16:16
 */
@Service
public class LivingStateServiceImpl implements LivingStateService {
    @Resource
    private LivingStateMapper livingStateMapper;

    @Override
    public boolean addLivingState(LivingStateDO livingState) {
        if (ObjectUtils.isEmpty(livingState)) {
            return false;
        }
        LambdaQueryWrapper<LivingStateDO> livingStateLambdaQueryWrapper = new LambdaQueryWrapper<LivingStateDO>()
                .eq(LivingStateDO::getLivingStateName, livingState.getLivingStateName());
        LivingStateDO livingStateQueryResult = livingStateMapper.selectOne(livingStateLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(livingStateQueryResult)) {
            livingStateMapper.insert(livingState);
        } else {
            livingState.setId(livingStateQueryResult.getId());
            livingStateMapper.updateById(livingState);
        }
        return true;
    }

    @Override
    public List<TreeNode<LivingStateDO>> getLivingStateTreeList(String id, Integer mode) {
        List<LivingStateDO> livingStateList = livingStateMapper.selectList(null);
        List<TreeNode<LivingStateDO>> nodeList = new ArrayList<>();
        for (LivingStateDO livingState : livingStateList) {
            TreeNode<LivingStateDO> node = new TreeNode<>();
            node.setId(livingState.getId());
            node.setContent(livingState);
            node.setParentId(livingState.getParentId());
            nodeList.add(node);
        }
        List<TreeNode<LivingStateDO>> treeNodeList;
        if (id != null) {
            treeNodeList = buildTreeByRoot(nodeList, id);
        } else {
            treeNodeList = buildTree(nodeList);
        }
        if (mode == 2) {
            return reverseBuildTree(treeNodeList);
        }
        return treeNodeList;
    }

    @Override
    public LivingStateDO getLivingStateDetail(String id) {
        return livingStateMapper.selectById(id);
    }

    @Override
    public boolean deleteLivingState(String id) {
        List<TreeNode<LivingStateDO>> livingStateTreeList = getLivingStateTreeList(id, 2);
        Set<Long> idSet = livingStateTreeList.stream().map(item -> Long.valueOf(item.getId())).collect(Collectors.toSet());
        livingStateMapper.deleteByIds(idSet);
        return true;
    }
}
