package com.wanderingverse.service.system;

import com.wanderingverse.model.dto.response.UserResponseDTO;
import com.wanderingverse.model.entity.UserDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lihui
 * @since 2025/9/18 11:20
 **/
public interface SystemUserService {

    /**
     * 获取当前系统用户信息
     *
     * @return UserResponseDTO
     */
    UserResponseDTO getCurrentSystemUser();


    /**
     * 根据用户 id 获取用户信息
     *
     * @param userId 用户 id
     * @return UserResponseDTO
     */
    UserResponseDTO getUserById(Long userId);

    /**
     * 更新用户头像
     *
     * @param userId 用户 id
     * @param file   MultipartFile
     * @return boolean
     * @throws IOException IOException
     */
    boolean updateUserAvatar(Long userId, MultipartFile file) throws IOException;

    /**
     * 更新用户信息
     *
     * @param user User
     * @return boolean
     */
    boolean updateUserInfo(UserDO user);
}
