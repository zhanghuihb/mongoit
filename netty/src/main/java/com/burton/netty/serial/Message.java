package com.burton.netty.serial;

import lombok.Data;

/**
 * 消息体
 * @author Tainy
 * @date 2018/12/27 14:37
 */
@Data
public class Message extends Serializer{

    private int msgType;

    private String content;

    @Override
    protected void read() {
        this.msgType = readInt();
        this.content = readString();
    }

    @Override
    protected void write() {
         writeInt(this.msgType);
         writeString(this.content);
    }
}
