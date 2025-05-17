package com.wanderingverse.controller.individualcontroller;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.model.entity.LivingStateDO;
import com.wanderingverse.service.individualservice.LivingStateService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 生活状态 Controller
 *
 * @author lihui
 * @date 2025/5/13 0:09
 **/
@RestController
@RequestMapping("/living-state")
@Slf4j
public class LivingStateController {
    @Resource
    private LivingStateService livingStateService;

    @PostMapping("/add")
    public AjaxResult addLivingState(@RequestBody LivingStateDO livingState) {
        boolean result = livingStateService.addLivingState(livingState);
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping("/list")
    public AjaxResult getLivingStateList() {
        List<LivingStateDO> livingStateList = livingStateService.getLivingStateList();
        return AjaxResult.success(livingStateList);
    }
}
