package com.burton.netty.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话管理者
 *
 * @author Tainy
 * @date 2018/12/20 18:57
 */
public class SessionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    /**
     * 在线会话
     */
    private static final ConcurrentHashMap<Integer, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * 加入
     * @param userId 用户ID
     * @param session
     * @return
     */
    public static boolean putSession(Integer userId, Session session){
        if(!onlineSessions.containsKey(userId)){
            boolean success = onlineSessions.putIfAbsent(userId, session)== null? true : false;
            return success;
        }
        return false;
    }

    /**
     * 移除
     * @param userId 用户ID
     */
    public static Session removeSession(Integer userId){
        return onlineSessions.remove(userId);
    }

    /**
     * 是否在线
     * @param userId 用户ID
     * @return
     */
    public static boolean isOnlinePlayer(Integer userId){
        return onlineSessions.containsKey(userId);
    }

    /**
     * 获取所有在线玩家
     * @return
     */
    public static Set<Integer> getOnlinePlayers() {
        return Collections.unmodifiableSet(onlineSessions.keySet());
    }
}
