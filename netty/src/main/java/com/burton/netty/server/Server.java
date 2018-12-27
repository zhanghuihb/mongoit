package com.burton.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tainy
 * @date 2018/12/20 15:40
 */
@Configuration
public class Server {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.boss.thread.count}")
    private int bossCount;

    @Value("${netty.worker.thread.count}")
    private int workerCount;

    @Value("${netty.keepAlive}")
    private boolean keepAlive;

    @Value("${netty.backlog}")
    private int backlog;

    @Bean
    public ServerBootstrap run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(bossCount);
        EventLoopGroup workerGroup = new NioEventLoopGroup(workerCount);

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, backlog)
                    .childOption(ChannelOption.SO_KEEPALIVE, keepAlive);

            // Bind and start to accept incoming connections.
            ChannelFuture f = bootstrap.bind(port).sync(); // (7)

            System.out.println("netty starting successing!");
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            // 释放资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

        return bootstrap;
    }
}
