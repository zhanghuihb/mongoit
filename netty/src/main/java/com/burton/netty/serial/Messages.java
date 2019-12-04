package com.burton.netty.serial;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息体
 * @author Tainy
 * @date 2018/12/27 14:37
 */
@Data
public class Messages implements Serializable{

    private int msgType;

    private String content;

}
