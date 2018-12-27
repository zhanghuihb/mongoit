package com.burton.netty.server;

import com.alibaba.fastjson.JSON;
import com.burton.common.domain.XcxUser;
import com.burton.netty.codc.Request;
import com.burton.netty.serial.Message;
import com.burton.netty.serial.Serializer;
import com.burton.netty.session.Session;
import com.burton.netty.session.SessionImpl;
import com.burton.netty.session.SessionManager;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author Tainy
 * @date 2018/12/20 16:04
 */
public class ServerHandler extends SimpleChannelInboundHandler<Request> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);
    private static ServerHandler serverHandler;

    @PostConstruct
    public void init(){
        serverHandler = this;
    }

    /**
     * 接收消息
     *
     * @author Tainy
     * @date   2018/12/20 17:20
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        System.out.println("receive client msg : " + JSON.toJSONString(request));
        System.out.println(new Message().readFromBytes(request.getData()));

        // 返回客户端消息
//        channelHandlerContext.writeAndFlush("I have received your msg : " + request.toString());
        handlerMessage(new SessionImpl(ctx.channel()), request);
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
            XcxUser user = (XcxUser) object;
            LOGGER.info("channel 绑定用户信息 {}", JSON.toJSONString(user));
            SessionManager.removeSession(user.getId());
        }
        System.out.println(String.format("客户端 id=%s 断线成功",ctx.channel().id()));
    }

    /**
     * 异常
     * @author Tainy
     * @date   2018/12/21 18:19
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常" + ctx.channel());
        super.exceptionCaught(ctx, cause);
    }


    /**
     * 消息处理
     * @param session
     * @param request
     */
    private void handlerMessage(Session session, Request request){

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
