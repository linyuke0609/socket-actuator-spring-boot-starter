package com.xiejr.actuator.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 客户端心跳约定
 * @author: xjr
 * @create: 2020-06-23 09:52
 **/
@ConfigurationProperties(prefix = "socket.actuator.server",ignoreInvalidFields = true,ignoreUnknownFields = true)
@Data
@Accessors(chain = true)
public class HeartCheck {

    /**
     * 客户端与服务端约定心跳间隔时间,默认30（秒）
     */
    private int leaseRenewalIntervalInSeconds=30;

    /**
     * 心跳最长间隔时间，默认90
     */
    private int leaseExpirationDurationInSeconds = 90;

    /**
     * 服务端剔除无效socket约定时间，默认60秒
     */
    private  int evictionIntervalTimerInMs=60;

    /**
     * 发现过期时是否立即移除,默认是
     */
    private boolean evitWhenTimeOut=true;
}
