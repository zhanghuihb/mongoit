package com.burton.netty.server;

import com.alibaba.fastjson.JSON;
import com.burton.netty.session.Session;
import com.burton.netty.session.SessionImpl;
import com.burton.netty.session.SessionManager;
import com.burton.netty.session.User;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tainy
 * @date 2018/12/20 16:04
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    public static final String DELIMITER = "_#_";

    private AtomicInteger connectNum = new AtomicInteger(0);

    /**
     * 接收消息
     *
     * @author Tainy
     * @date   2018/12/20 17:20
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object object) throws Exception {
        int sign = 1;
        String msg = "";
        if (object instanceof String) {
            msg = (String) object;
        } else if (object instanceof TextWebSocketFrame) {
            sign = 2;
            msg = ((TextWebSocketFrame) object).text();
        } else {
            System.out.println("收到未知类型的消息："+msg);
            return;
        }

        System.out.println("receive client msg : " + msg);

        // 返回客户端消息
        ctx.writeAndFlush("I have received your msg : " + msg);
        handlerMessage(new SessionImpl(ctx.channel()), msg);
    }

    /**
     * 客户端连接到服务端后执行
     * @author Tainy 
     * @date   2018/12/20 18:16
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接到服务端成功" + ctx.channel());
        super.channelActive(ctx);

        int connections = connectNum.incrementAndGet();
        log.info("connections = {}", connections);
    }

    /**
     * 断线移除会话
     * @author Tainy 
     * @date   2018/12/20 18:42
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Session session = new SessionImpl(ctx.channel());
        Object object = session.getAttachment();
        if (object != null) {
            User user = (User) object;
            log.info("channel 绑定用户信息 {}", JSON.toJSONString(user));
            SessionManager.removeSession(user.getId());
        }

        log.info(String.format("客户端 id=%s 断线成功", ctx.channel().id()));
        super.channelInactive(ctx);
        log.info("[channelInactive]channel.isActive() = {}", ctx.channel().isActive());

        connectNum.decrementAndGet();
    }

    /**
     * 异常
     * @author Tainy
     * @date   2018/12/21 18:19
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("异常" + ctx.channel());
        super.exceptionCaught(ctx, cause);
        log.info("[exceptionCaught]channel.isActive() = {}", ctx.channel().isActive());
        ctx.close();
    }


    /**
     * 消息处理
     * @param session
     * @param msg
     */
    private void handlerMessage(Session session, String msg){
        // 握手消息

        // 业务消息

    }

    /**
     * 心跳超时-业务逻辑处理模块
     * @author Tainy
     * @date   2018/12/24 9:44
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered事件触发 idelState = " + ((IdleStateEvent) evt).state());
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            // 读超时
            if(Arrays.asList(IdleState.READER_IDLE, IdleState.WRITER_IDLE, IdleState.ALL_IDLE).contains(event.state())){
                System.out.println("--------------------------------------------");
                System.out.println("很长时间没有接收到客户端消息了,自动关闭channel");
                //关闭channel
                final ChannelFuture writeAndFlush = ctx.channel().writeAndFlush("Time Out,you will closed!");
                writeAndFlush.addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        ctx.channel().close();
                    }
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }

    }

}
