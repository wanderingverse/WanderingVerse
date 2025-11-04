package com.wanderingverse.service.individual.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderingverse.mapper.individual.LivingStateMapper;
import com.wanderingverse.model.TreeNode;
import com.wanderingverse.model.entity.LivingStateDO;
import com.wanderingverse.service.individual.LivingStateService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
    public List<TreeNode<LivingStateDO>> getLivingStateList(String id, Integer mode) {
        LambdaQueryWrapper<LivingStateDO> livingStateLambdaQueryWrapper = new LambdaQueryWrapper<LivingStateDO>();
        List<LivingStateDO> livingStateList = livingStateMapper.selectList(livingStateLambdaQueryWrapper);
        List<TreeNode<LivingStateDO>> nodeList = new ArrayList<>();
        for (LivingStateDO livingState : livingStateList) {
            TreeNode<LivingStateDO> node = new TreeNode<>();
            node.setId(livingState.getId());
            node.setContent(livingState);
            node.setParentId(livingState.getParentId());
            nodeList.add(node);
        }
        List<TreeNode<LivingStateDO>> treeNodeList = new ArrayList<>();
        if (StringUtils.hasText(id)) {
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
    public LivingStateDO getLivingStateDetail(Long id) {
        return livingStateMapper.selectById(id);
    }
}
