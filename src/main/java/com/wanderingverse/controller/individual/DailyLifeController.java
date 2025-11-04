package com.wanderingverse.controller.individual;

import com.wanderingverse.model.entity.DailyLifeDO;
import com.wanderingverse.service.individual.DailyLifeService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人生活 Controller
 *
 * @author lihui
 * @since 2025/5/10 17:00
 **/
@Slf4j
@RestController
@RequestMapping("/daily/life")
public class DailyLifeController {
    @Resource
    private DailyLifeService dailyLifeService;

    /**
     * 新增日常记录
     */
    @PostMapping("")
    public AjaxResult addDailyLife(@RequestBody @Valid DailyLifeDO dailyLife) {
        boolean result = dailyLifeService.addDailyLife(dailyLife);
        return result ? AjaxResult.success() : AjaxResult.error();
    }
}
