package com.wanderingverse.controller.systemcontroller;

import com.mzt.logapi.starter.annotation.LogRecord;
import com.wanderingverse.model.entity.UserDO;
import com.wanderingverse.service.systemservice.SystemLoginService;
import com.wanderingverse.util.AjaxResult;
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
 * @since 2025/5/7 22:10
 **/
@Slf4j
@RestController
@RequestMapping("/system/login")
public class SystemLoginController {
    @Resource
    private SystemLoginService systemLoginService;


    /**
     * 用户登录
     *
     * @param user 登录用户
     */
    @LogRecord(success = "{{#user.username}}执行了测试方法1，请求了：{{#user.username}}，返回：{{#_ret}}",
            fail = "用户登录失败。失败原因：{{#_errorMsg}}",
            type = "用户测试",
            bizNo = "0001")
    @PostMapping("")
    public AjaxResult login(@RequestBody UserDO user) {
        String token = systemLoginService.login(user);
        return AjaxResult.success();
//        throw new IllegalArgumentException("测试异常");
    }
}
