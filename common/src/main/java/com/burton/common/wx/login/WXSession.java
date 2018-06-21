package com.burton.common.wx.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/6/11 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WXSession implements Serializable{

    private String openid;

    private String session_key;

    private String unionid;
}
