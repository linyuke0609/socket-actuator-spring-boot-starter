package com.xiejr.actuator.exceptioin;

import lombok.Data;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 自定义异常
 * @author: xjr
 * @create: 2020-06-22 10:02
 **/
@Data
public class SocketActuatorException extends RuntimeException {

    private String error;

    public SocketActuatorException(String error){
       this.error=error;
    }
}
