package com.wanderingverse.service.individual.impl;

import com.wanderingverse.ex.UnprocessableEntityException;
import com.wanderingverse.mapper.individual.DailyLifeMapper;
import com.wanderingverse.model.entity.DailyLifeDO;
import com.wanderingverse.model.entity.LivingStateDO;
import com.wanderingverse.service.individual.DailyLifeService;
import com.wanderingverse.service.individual.LivingStateService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lihui
 * @since 2025/9/12 17:45
 **/
@Service
public class DailyLifeServiceImpl implements DailyLifeService {
    @Resource
    private DailyLifeMapper dailyLifeMapper;

    @Resource
    @Lazy
    private LivingStateService livingStateService;


    @Override
    public boolean addDailyLife(DailyLifeDO dailyLife) {
        LivingStateDO livingState = livingStateService.getLivingStateDetail(dailyLife.getLivingStateId());
        if (ObjectUtils.isEmpty(livingState)) {
            throw new UnprocessableEntityException("生活状态不存在");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        BigDecimal defaultAmount = BigDecimal.ZERO;
        DailyLifeDO dailyLifeForInsert = new DailyLifeDO()
                .setLivingStateId(dailyLife.getLivingStateId())
                .setBeginningTime(ObjectUtils.isEmpty(dailyLife.getBeginningTime()) ? currentTime : dailyLife.getBeginningTime())
                .setFinishingTime(ObjectUtils.isEmpty(dailyLife.getFinishingTime()) ? currentTime : dailyLife.getFinishingTime())
                .setIncomeAmount(ObjectUtils.isEmpty(dailyLife.getIncomeAmount()) ? defaultAmount : dailyLife.getIncomeAmount())
                .setExpenditureAmount(ObjectUtils.isEmpty(dailyLife.getExpenditureAmount()) ? defaultAmount : dailyLife.getExpenditureAmount())
                .setRemark(dailyLife.getRemark());
        return dailyLifeMapper.insert(dailyLifeForInsert) > 0;
    }
}
