package com.mongoit.common.dubbo.user;

/**
 * @author Tainy
 * @date 2018/6/23
 */
public interface UserDubboService {

    /** 定时刷新AccessToken  */
    void refreshAccessToken();

}
