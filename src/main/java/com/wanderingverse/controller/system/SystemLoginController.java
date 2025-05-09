package com.wanderingverse.controller.system;

import com.wanderingverse.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 系统登录 Controller
 *
 * @author lihui
 * @date 2025/5/7 22:10
 **/
@RestController
@RequestMapping("/system/login")
@Slf4j
public class SystemLoginController {
    @PostMapping("")
    public AjaxResult login(String username, String password) {
        log.info("用户名：{}，密码：{}", username, password);
        return AjaxResult.success("登录成功");
    }
}
