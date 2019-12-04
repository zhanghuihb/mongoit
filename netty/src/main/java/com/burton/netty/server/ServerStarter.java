package com.burton.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/12/20 16:04
 */
@Slf4j
@Component
public class ServerStarter {

    @Resource(name = "serverBootstrap")
    private ServerBootstrap serverBootstrap;

    @Value("${netty.port}")
    private int tcpPort;

    private List<ChannelFuture> serverChannelFuture;

    @PostConstruct
    public void start() throws Exception {
        log.info("netty server starting at {}", tcpPort);

        serverChannelFuture = new ArrayList<>();
        serverChannelFuture.add(serverBootstrap.bind(new InetSocketAddress(tcpPort)).sync());
        /*serverChannelFuture.add(bootstrap.bind(8192).sync());
        serverChannelFuture.add(bootstrap.bind(8193).sync());*/
    }

    @PreDestroy
    public void stop() throws Exception {
        for (ChannelFuture channelFuture : serverChannelFuture) {
            channelFuture.channel().closeFuture().sync();
        }
    }

}
