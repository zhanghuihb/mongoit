package com.mongoit.netty.session;

/**
 * 会话抽象session接口
 *
 * @author Tainy
 * @date 2018/12/20 18:47
 */
public interface Session {

    /**
     * 会话绑定对象
     * @return
     */
    Object getAttachment();

    /**
     * 绑定对象
     * @return
     */
    void setAttachment(Object attachment);

    /**
     * 移除绑定对象
     * @return
     */
    void removeAttachment();

    /**
     * 向会话中写入消息
     * @param message
     */
    void write(Object message);

    /**
     * 判断会话是否在连接中
     * @return
     */
    boolean isConnected();

    /**
     * 关闭
     * @return
     */
    void close();
}
