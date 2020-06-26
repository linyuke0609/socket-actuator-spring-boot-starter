package com.xiejr.actuator.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 监控可配置信息实体
 * @author: xjr
 * @create: 2020-06-16 15:49
 **/
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "socket.actuator",ignoreUnknownFields = true,ignoreInvalidFields = true)
public class ActuatorBaseInfo {

    private String systemName;

    private boolean simplePageApplication;

    private String author;

    private String version;

    private String ipAddress;

}
