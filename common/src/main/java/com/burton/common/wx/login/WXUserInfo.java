package com.burton.common.wx.login;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/12 15:03
 */
@Data
public class WXUserInfo implements Serializable{

    private String openId;

    private String nickName;

    private String gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    private String unionId;

    private WaterMark watermark;
}
