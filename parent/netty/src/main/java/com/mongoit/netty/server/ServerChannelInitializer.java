package com.mongoit.netty.server;

import com.mongoit.netty.codc.ProtocolDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 服务器Channel通道初始化设置
 *
 * @author Tainy
 * @date 2018/12/20 17:55
 */
@Component("nettyChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ServerHandler serverHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 心跳检测,300秒没有读操作，自动断开连接。
        pipeline.addLast("idle", new IdleStateHandler(300, 0, 0));

        // 协议解码处理器，判断是什么协议（WebSocket还是TcpSocket）,然后动态修改编解码器
        pipeline.addLast("protocolDecoder", new ProtocolDecoder());

        // 针对socket的解码器(WebSocket基于http，TcpSocket基于tcp, 底层都是基于tcp，以下为通用解码配置)
        ByteBuf delimiter = Unpooled.copiedBuffer(ServerHandler.DELIMITER.getBytes());
        pipeline.addLast("delimiter", new DelimiterBasedFrameDecoder(4096, delimiter));
        pipeline.addLast("stringDecoder", new StringDecoder());
        pipeline.addLast("stringEncoder", new StringEncoder());

        // 服务器逻辑
        pipeline.addLast("hanlder", serverHandler);
    }
}
