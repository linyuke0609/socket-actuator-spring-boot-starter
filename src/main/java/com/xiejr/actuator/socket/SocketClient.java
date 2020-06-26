package com.xiejr.actuator.socket;

import com.xiejr.actuator.entity.BaseEntity;
import com.xiejr.actuator.model.Message;
import lombok.*;
import lombok.experimental.Accessors;
import javax.websocket.Session;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 客户端实体
 * @author: xjr
 * @create: 2020-06-23 11:19
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocketClient extends BaseEntity {

    /**
     * 设备IP
     */
    private String deviceIp;


    /**
     * 设备连接的时间
     */
    private LocalDateTime conectTime;

    /**
     * 设备是否断开
     */
    private Boolean close;

    /**
     * 本次连接所维护的会话
     */
    private Session session;

    /**
     * 上一次心跳传递时间
     */
    private LocalDateTime lastRefreshTime;

    /**
     * 一次连接当中客户端与服务端所维护的所有历史消息
     */
    private LinkedHashMap<String, Message> history;
}
