package com.xiejr.actuator.socket;

import com.alibaba.fastjson.JSON;
import com.xiejr.actuator.enums.RoleEnum;
import com.xiejr.actuator.model.Message;
import com.xiejr.actuator.util.WebsocketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: websocket配置类
 * @author: xjr
 * @create: 2020-06-18 15:31
 **/
@ServerEndpoint("/socket/{ip}")
@Component("actuator-socket")
@Slf4j
public class WebSocketConfig {
    private static AtomicInteger onlineCount=new AtomicInteger(0);

    public static Map<String,SocketClient> socketConfigMap=new ConcurrentHashMap<>(20);


    private Session session;

    private static final DateTimeFormatter DATE_TIME_FORMATTER=DateTimeFormatter.ofPattern("dd日 hh时:mm分:ss秒");




    /**
     *
     * @description 方法描述
     * @param session:新会话  ip:连接Ip
     * @author xiejiarong
     * @date 2020年06月22日 14:11
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("ip") String ip) throws Exception {
        this.session=session;
        InetSocketAddress ipAddress= WebsocketUtils.getRemoteAddress(session);
        LocalDateTime now=LocalDateTime.now();
        String time=DATE_TIME_FORMATTER.format(now);
        String sessionId=session.getId();
        LinkedHashMap<String,Message> history=new LinkedHashMap<>(50);
        history.put(time,new Message().setFrom(RoleEnum.CLIENT.getCode()).setContent("客户端首次连接").setTo(RoleEnum.SERVER.getCode())
        .setSendTime(time));
        SocketClient socketClient=SocketClient.builder()
                .close(false)
                .conectTime(now)
                .session(session)
                .deviceIp(ip)
                .lastRefreshTime(now)
                .history(history)
                .build();
        if (socketConfigMap.containsKey(sessionId)) {
            socketConfigMap.remove(sessionId);
            socketConfigMap.put(sessionId, socketClient);
        } else {
            socketConfigMap.put(sessionId, socketClient);
            addOnlineCount();
        }
        log.info("有ip为{}的新设备接入,当前设备总数:{}",ip,getOnlineCount());
    }

    /**
     *
     * @description 连接出现错误
     * @param session: 当前会话  error:错误
     * @author xiejiarong
     * @date 2020年06月22日 14:20
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("连接设备:{}错误,原因:{}"  ,socketConfigMap.get(session.getId()).getDeviceIp(),error.getMessage());
        error.printStackTrace();
    }


    /**
     *
     * @description 服务端监听客户端消息
     * @param message: 客户端报文  session:会话
     * @author xiejiarong
     * @date 2020年06月22日 14:20
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        refreshHeart(LocalDateTime.now(),message);
        log.info("设备{}发来消息,报文内容:{}",socketConfigMap.get(session.getId()).getDeviceIp(),message);
    }

    /**
     *
     * @description 服务端监听socket断开
     * @author xiejiarong
     * @date 2020年06月22日 14:21
     */
    @OnClose
    public void onClose() {
        String exitIp=socketConfigMap.get(this.session.getId()).getDeviceIp();
            if (socketConfigMap.containsKey(this.session.getId())){
                socketConfigMap.remove(this.session.getId());
                subOnlineCount();
            }
        log.info("设备{}退出,当前连接设备数:{}",exitIp,getOnlineCount());


    }

    /**
     *
     * @description 获取当前socket连接数
     * @author xiejiarong
     * @date 2020年06月22日 14:09
     */
    public static  int getOnlineCount() {
        return onlineCount.get();
    }

    /**
     *
     * @description 服务端主动推送消息
     * @param message:
     * @author xiejiarong
     * @date 2020年06月22日 14:13
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     *
     * @description 当前连接数+1
     * @author xiejiarong
     * @date 2020年06月22日 14:10
     */
    public static  void addOnlineCount() {
       onlineCount.incrementAndGet();
    }

    /**
     *
     * @description 当前连接数-1
     * @author xiejiarong
     * @date 2020年06月22日 14:10
     */
    public static  void subOnlineCount() {
       onlineCount.decrementAndGet();
    }


    /**
     *
     * @description 刷新心跳时间
     * @param refreshTime:当前刷新事件
     * @author xiejiarong
     * @date 2020年06月23日 14:14
     */
    public  void refreshHeart(LocalDateTime refreshTime,String message){
        String time=DATE_TIME_FORMATTER.format(refreshTime);
        socketConfigMap.get(this.session.getId()).setLastRefreshTime(refreshTime).setClose(false).getHistory().put(time,
                new Message().setSendTime(time).setTo(RoleEnum.SERVER.getCode())
        .setContent(message).setFrom(RoleEnum.CLIENT.getCode()));
    }
}
