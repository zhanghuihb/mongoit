package com.burton.common.dubbo.lanbitou;

/**
 * @author Tainy
 * @date 2018/6/21 19:14
 */
public interface LanbitouDubboService {

    /** 定时刷新AccessToken  */
    void refreshAccessToken();

}
