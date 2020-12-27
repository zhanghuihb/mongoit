package com.mongoit.netty.codc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 协议解码处理器，用来判断使用的什么协议（本例是指WebSocket还是TcpSocket），从而动态修改编解码器
 *
 * @author Tainy
 * @date 2019-11-15 19:59
 */
@Slf4j
public class ProtocolDecoder extends ByteToMessageDecoder {

    /**
     * 请求行信息的长度，ws为：GET /ws HTTP/1.1， Http为：GET / HTTP/1.1
     */
    private static final int PROTOCOL_LENGTH = 16;

    /**
     * WebSocket握手协议的前缀， 本例限定为：GET /ws ，在访问ws的时候，请求地址需要为如下格式 ws://ip:port/ws
     */
    private static final String WEBSOCKET_PREFIX = "GET /ws";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String protocol = getBufStart(in);
        ChannelPipeline pipeline = ctx.channel().pipeline();
        log.info("ProtocolHandler protocol:" + protocol);
        if (protocol.startsWith(WEBSOCKET_PREFIX)) {
            // HttpServerCodec：将请求和应答消息解码为HTTP消息
            pipeline.addBefore("handler", "http-codec", new HttpServerCodec());
            // HttpObjectAggregator：将HTTP消息的多个部分合成一条完整的HTTP消息
            pipeline.addBefore("handler", "aggregator", new HttpObjectAggregator(65535));
            // ChunkedWriteHandler：向客户端发送HTML5文件,文件过大会将内存撑爆
            pipeline.addBefore("handler", "http-chunked", new ChunkedWriteHandler());
            pipeline.addBefore("handler", "WebSocketAggregator", new WebSocketFrameAggregator(65535));
            //用于处理websocket, /ws为访问websocket时的uri
            pipeline.addBefore("handler", "ProtocolHandler", new WebSocketServerProtocolHandler("/ws"));

            // 移除可能缓存的TcpSocket解码器
            pipeline.remove(DelimiterBasedFrameDecoder.class);
            pipeline.remove(StringDecoder.class);
        }

        // 重置index标记位
        in.resetReaderIndex();

        // 移除该协议处理器，该channel后续的处理由对应协议安排好的编解码器处理
        pipeline.remove(this.getClass());
    }

    /**
     * 获取buffer中指定长度的信息
     *
     * @param in
     * @return
     */
    private String getBufStart(ByteBuf in) {
        int length = in.readableBytes();
        if (length > PROTOCOL_LENGTH) {
            length = PROTOCOL_LENGTH;
        }
        // 标记读取位置
        in.markReaderIndex();
        byte[] content = new byte[length];
        in.readBytes(content);
        return new String(content);
    }
}
