package com.wanderingverse.model.dto.response;

import com.wanderingverse.model.entity.UserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lihui
 * @since 2025/9/18 12:35
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserResponseDTO extends UserDO {

    /**
     * 头像预签名 url
     */
    private String avatarPreSignedUrl;
}
