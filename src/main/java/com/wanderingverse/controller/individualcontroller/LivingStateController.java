package com.wanderingverse.controller.individualcontroller;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.model.TreeNode;
import com.wanderingverse.model.entity.LivingStateDO;
import com.wanderingverse.service.individualservice.LivingStateService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 生活状态 Controller
 *
 * @author WanderingVerse
 * @since 2025/08/17 13:24
 **/
@Slf4j
@RestController
@RequestMapping("/living-state")
public class LivingStateController {
    @Resource
    private LivingStateService livingStateService;

    /**
     * 添加生活状态
     */
    @PostMapping("/add")
    public AjaxResult addLivingState(@RequestBody @Valid LivingStateDO livingState) {
        boolean result = livingStateService.addLivingState(livingState);
        return result ? AjaxResult.success() : AjaxResult.error();
    }


    /**
     * 获取生活状态列表
     */
    @GetMapping("/list")
    public AjaxResult getLivingStateList(@RequestParam(required = false) String id,
                                         @RequestParam(defaultValue = "1") Integer mode) {
        List<TreeNode<LivingStateDO>> livingStateList = livingStateService.getLivingStateList(id, mode);
        return AjaxResult.success(livingStateList);
    }
}
