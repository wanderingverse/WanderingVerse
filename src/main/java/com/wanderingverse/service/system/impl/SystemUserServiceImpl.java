package com.wanderingverse.service.system.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.wanderingverse.config.MinioConfig;
import com.wanderingverse.ex.ServiceException;
import com.wanderingverse.mapper.system.UserMapper;
import com.wanderingverse.model.dto.response.UserResponseDTO;
import com.wanderingverse.model.entity.UserDO;
import com.wanderingverse.service.system.SystemUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.wanderingverse.config.CommonConfig.IMAGE_DIRECTORY;
import static com.wanderingverse.config.CommonConfig.USER_FILE_DIRECTORY;
import static com.wanderingverse.util.FileUtils.generateUniqueFileName;

/**
 * @author lihui
 * @since 2025/9/18 11:20
 **/
@Service
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MinioConfig minioConfig;

    @Override
    public UserResponseDTO getCurrentSystemUser() {
        return getUserById(1L);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        MPJLambdaWrapper<UserDO> userLambdaQueryWrapper = new MPJLambdaWrapper<UserDO>()
                .eq(UserDO::getId, userId);
        UserResponseDTO user = userMapper.selectJoinOne(UserResponseDTO.class, userLambdaQueryWrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("用户信息获取失败");
        }
        String preSignedUrl = minioConfig.getPreSignedUrl(user.getAvatar(), null);
        user.setAvatarPreSignedUrl(preSignedUrl);
        return user;
    }

    @Override
    public boolean updateUserAvatar(Long userId, MultipartFile file) throws IOException {
        // fixme 校验文件类型
        String fileName = generateUniqueFileName(file);
        fileName = USER_FILE_DIRECTORY + File.separator + IMAGE_DIRECTORY + File.separator + fileName;
        boolean result = minioConfig.uploadFile(fileName, file.getBytes());
        if (!result) {
            throw new ServiceException("用户头像上传失败");
        }
        // 删除原头像
        UserResponseDTO userForDelete = getUserById(userId);
        boolean deleted = minioConfig.deleteFile(userForDelete.getAvatar());
        // 更新用户头像
        UserDO user = new UserDO().setId(userId).setAvatar(fileName);
        boolean updateUserInfoResult = updateUserInfo(user);
        return updateUserInfoResult && deleted;
    }

    @Override
    public boolean updateUserInfo(UserDO user) {
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getId())) {
            throw new ServiceException("用户信息为空");
        }
        return userMapper.updateById(user) > 0;
    }
}
