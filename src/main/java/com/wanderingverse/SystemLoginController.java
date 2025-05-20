package com.wanderingverse;

import com.wanderingverse.common.AjaxResult;
import com.wanderingverse.model.entity.UserDO;
import com.wanderingverse.service.systemservice.SystemLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Resource
    private SystemLoginService systemLoginService;

    @PostMapping("")
    public AjaxResult login(@RequestBody UserDO user) {
//        systemLoginService.login(user);
        return AjaxResult.success("登录成功");
    }
}
