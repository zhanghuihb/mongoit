package com.burton.netty.codc;

import com.burton.netty.constant.ConstantValue;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.MessageToByteEncoder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;


/**
 * 请求编码器
 *
 * 数据包格式
 * +--------+---------+-------+-------+--------+
 * |  包头  | userId  | 状态码 | 长度  |   数据  |
 * +-------+---------+-------+-------+--------+
 * 包头 4个字节
 * userId 4个字节
 * 状态码 4个字节
 * 数据长度 4个字节（描述数据部分字节长度）
 *
 * @author Tainy
 * @date 2018/12/26 11:58
 */
public class ResponseEncoder extends MessageToByteEncoder<Response> {
    @Override
    protected void encode(io.netty.channel.ChannelHandlerContext ctx, Response response, ByteBuf buffer) throws Exception {
        // 包头
        buffer.writeInt(ConstantValue.BAOTOU);
        // userId
        buffer.writeInt(response.getUserId());
        // 状态码
        buffer.writeInt(response.getStateCode());
        // 长度
        buffer.writeInt(response.getDataLength());
        // 数据
        int lenth = response.getData()==null? 0 : response.getData().length;
        if(lenth <= 0){
            buffer.writeInt(lenth);
        }else{
            buffer.writeInt(lenth);
            buffer.writeBytes(response.getData());
        }
    }
}
