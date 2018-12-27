package com.burton.netty.server;

import com.burton.netty.codc.RequestDecoder;
import com.burton.netty.codc.ResponseEncoder;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 服务器Channel通道初始化设置
 *
 * @author Tainy
 * @date 2018/12/20 17:55
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 字符串编码和解码
        pipeline.addLast("decoder", new RequestDecoder());
        pipeline.addLast("encoder", new ResponseEncoder());
        // 服务器逻辑
        pipeline.addLast("hanlder", new ServerHandler());
    }
}
