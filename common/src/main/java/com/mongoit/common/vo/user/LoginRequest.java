package com.mongoit.common.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Tainy
 * @date 2018/6/11 15:24
 */
@Data
public class LoginRequest {

    @NotNull(message = "用户授权code不能为空")
    @ApiModelProperty(value = "用户授权code", required = true)
    private String code;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private Byte sex;

    @ApiModelProperty("头像")
    private String headImgUrl;

    @ApiModelProperty("用户授权登陆加密密文")
    private String encryptedData;

    @ApiModelProperty("加密偏移量")
    private String iv;
}
