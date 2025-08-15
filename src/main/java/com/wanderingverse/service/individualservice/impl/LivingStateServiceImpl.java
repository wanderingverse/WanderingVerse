package com.wanderingverse.service.individualservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderingverse.mapper.individualmapper.LivingStateMapper;
import com.wanderingverse.model.entity.LivingStateDO;
import com.wanderingverse.service.individualservice.LivingStateService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;


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
    public List<LivingStateDO> getLivingStateList() {
        List<LivingStateDO> livingStateList = new ArrayList<>();
        LambdaQueryWrapper<LivingStateDO> livingStateLambdaQueryWrapper = new LambdaQueryWrapper<LivingStateDO>();
        livingStateList = livingStateMapper.selectList(livingStateLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(livingStateList)) {
            return livingStateList;
        }
        return livingStateList;
    }
}
