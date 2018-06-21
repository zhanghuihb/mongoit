package com.burton.common.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/11 15:40
 */
@Data
public class LoginResponse implements Serializable{

    @ApiModelProperty("用户token")
    private String token;

    @ApiModelProperty("用户openId")
    private String openId;

    @ApiModelProperty("用户unionId")
    private String unionId;

    @ApiModelProperty("session_key")
    private String sessionKey;

    @ApiModelProperty("nickName")
    private String nickName;

    @ApiModelProperty("gender")
    private String gender;

    @ApiModelProperty("avatarUrl")
    private String avatarUrl;

    @ApiModelProperty("xcxUserId")
    private Integer xcxUserId;
}
