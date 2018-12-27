package com.burton.netty.codc;

import com.burton.netty.constant.ConstantValue;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 请求解码器
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
 * @date 2018/12/26 16:30
 */
public class ResponseDecoder extends ByteToMessageDecoder {

    /**
     * 数据包基本长度
     */
    public static int BASE_LENGTH = 4 + 4 + 4 + 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        while(true){
            if(buffer.readableBytes() >= BASE_LENGTH){
                // 第一个可读数据包的起始位置
                int beginIndex;

                while(true) {
                    // 包头开始游标点
                    beginIndex = buffer.readerIndex();
                    // 标记初始读游标位置
                    buffer.markReaderIndex();
                    if (buffer.readInt() == ConstantValue.BAOTOU) {
                        break;
                    }
                    // 未读到包头标识略过一个字节
                    buffer.resetReaderIndex();
                    buffer.readByte();

                    // 不满足
                    if(buffer.readableBytes() < BASE_LENGTH){
                        return;
                    }
                }
                // userId
                int userId = buffer.readInt();
                // stateCode
                int stateCode = buffer.readInt();

                // 读取数据长度
                int lenth = buffer.readInt();
                if(lenth < 0 ){
                    ctx.channel().close();
                }

                // 数据包还没到齐
                if(buffer.readableBytes() < lenth){
                    buffer.readerIndex(beginIndex);
                    return;
                }

                // 读数据部分
                byte[] data = new byte[lenth];
                buffer.readBytes(data);

                Response response = new Response();
                response.setUserId(userId);
                response.setStateCode(stateCode);
                response.setData(data);
                // 解析出消息对象，继续往下面的handler传递
                out.add(response);
            }else{
                break;
            }
        }
        // 数据不完整，等待完整的数据包
        return ;
    }
}
