package com.mongoit.netty.client;

import com.alibaba.fastjson.JSON;
import com.mongoit.netty.serial.Messages;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * 客服端
 * @author Tainy
 * @date 2018/12/27 14:11
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 服务类
        Bootstrap bootstrap = new Bootstrap();
        // 线程池
        bootstrap.group(new NioEventLoopGroup());
        // channel工厂
        bootstrap.channel(NioSocketChannel.class);
        // 管道工厂
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("stringDecoder", new StringDecoder());
                ch.pipeline().addLast("stringEncoder", new StringEncoder());
                ch.pipeline().addLast("clienthandler", new ClientHandler());
            }
        });

        // 连接服务器
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8707));
        Channel channel = connect.sync().channel();

        System.out.println("client has already started!");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("请输入发送的内容：");
            Messages message = new Messages();
            message.setMsgType(1);
            message.setContent(scanner.nextLine());

            // 发送请求
            channel.writeAndFlush(JSON.toJSONString(message));
        }
    }
}
