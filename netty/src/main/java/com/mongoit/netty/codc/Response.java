package com.mongoit.netty.codc;

import lombok.Data;

/**
 * 返回对象
 * @author Tainy
 * @date 2018/12/26 11:56
 */
@Data
public class Response {

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 状态码
     */
    private int stateCode;

    /**
     * 数据
     */
    private byte[] data;

    /**
     * 数据长度
     * @return
     */
    public int getDataLength(){
        if(data == null){
            return 0;
        }

        return data.length;
    }
}
