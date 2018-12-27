package com.burton.netty.codc;

import lombok.Data;

/**
 * 请求对象
 * @author Tainy
 * @date 2018/12/26 11:51
 */
@Data
public class Request {

    /**
     * 用户ID
     */
    private int userId;

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
