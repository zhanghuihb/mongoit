package com.burton.netty.client;

import com.alibaba.fastjson.JSON;
import com.burton.netty.codc.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Tainy
 * @date 2018/12/27 14:17
 */
public class ClientHandler extends SimpleChannelInboundHandler<Response>{

    /**
     * 接收消息
     * @param ctx
     * @param response
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        System.out.println(String .format("Client[id=%s] receive msg %s: ",ctx.channel().id(), JSON.toJSONString(response)));
    }

    /**
     * 建立连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(String.format("客户端 id=%s 连接到服务端成功",ctx.channel().id()));
        super.channelActive(ctx);
    }

    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(String.format("客户端 id=%s 断线成功",ctx.channel().id()));
        super.channelInactive(ctx);
    }

    /**
     * 异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常" + ctx.channel());
        super.exceptionCaught(ctx, cause);
    }
}
