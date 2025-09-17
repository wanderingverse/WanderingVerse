package com.wanderingverse.controller.systemcontroller;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.service.systemservice.SystemMenuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单项 Controller
 *
 * @author lihui
 * @since 2025/9/17 19:28
 **/
@Slf4j
@RestController
@RequestMapping("/system/menu")
public class SystemMenuController {
    @Resource
    private SystemMenuService systemMenuService;

    /**
     * 获取菜单项
     */
    @GetMapping("")
    public AjaxResult getMenu() {
        return AjaxResult.success("查询成功");
    }
}
