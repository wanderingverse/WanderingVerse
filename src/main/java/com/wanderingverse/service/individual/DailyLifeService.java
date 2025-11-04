package com.wanderingverse.service.individual;

import com.wanderingverse.model.entity.DailyLifeDO;

/**
 * @author lihui
 * @since 2025/9/12 17:45
 **/
public interface DailyLifeService {

    /**
     * 新增日常记录
     *
     * @param dailyLife DailyLifeDO
     * @return boolean
     */
    boolean addDailyLife(DailyLifeDO dailyLife);
}
