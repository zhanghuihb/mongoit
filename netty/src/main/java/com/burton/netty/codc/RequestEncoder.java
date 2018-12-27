package com.burton.netty.codc;

import com.burton.netty.constant.ConstantValue;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 请求编码器
 *
 * 数据包格式
 * +--------+---------+-------+-------+--------+
 * |  包头  | unionId | appId |  长度  |  数据  |
 * +-------+---------+-------+--------+-------+
 * 包头 4个字节
 * unionId 4个字节
 * appId 4个字节
 * 数据长度 4个字节（描述数据部分字节长度）
 *
 * @author Tainy
 * @date 2018/12/26 11:58
 */
public class RequestEncoder extends MessageToByteEncoder<Request> {

    @Override
    protected void encode(io.netty.channel.ChannelHandlerContext ctx, Request request, ByteBuf buffer) throws Exception {
        // 包头
        buffer.writeInt(ConstantValue.BAOTOU);
        // userId
        buffer.writeInt(request.getUserId());
        // 长度
        buffer.writeInt(request.getDataLength());
        // 数据
        int lenth = request.getData()==null? 0 : request.getData().length;
        if(lenth <= 0){
            buffer.writeInt(lenth);
        }else{
            buffer.writeInt(lenth);
            buffer.writeBytes(request.getData());
        }
    }
}
