package com.wanderingverse.controller.system;

import com.wanderingverse.model.dto.response.UserResponseDTO;
import com.wanderingverse.service.system.SystemUserService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 系统用户 Controller
 *
 * @author lihui
 * @since 2025/9/18 11:14
 **/
@Slf4j
@RestController
@RequestMapping("/system/user")
public class SystemUserController {
    @Resource
    private SystemUserService systemUserService;

    /**
     * 获取当前登录系统的用户信息
     */
    @GetMapping("")
    public AjaxResult getCurrentSystemUser() {
        UserResponseDTO user = systemUserService.getCurrentSystemUser();
        return !ObjectUtils.isEmpty(user) ? AjaxResult.success(user) : AjaxResult.error("用户信息获取失败");
    }

    /**
     * 更新用户头像
     */
    @PutMapping("/avatar")
    public AjaxResult updateUserAvatar(@RequestParam String userId, @RequestParam MultipartFile file) throws IOException {
        boolean result = systemUserService.updateUserAvatar(userId, file);
        return result ? AjaxResult.success() : AjaxResult.error("用户头像更新失败");
    }
}
