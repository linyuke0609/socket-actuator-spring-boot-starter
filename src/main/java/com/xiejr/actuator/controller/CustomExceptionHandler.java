package com.xiejr.actuator.controller;

import com.xiejr.actuator.exceptioin.SocketActuatorException;
import com.xiejr.actuator.model.ResultVO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 自定义异常处理
 * @author: xjr
 * @create: 2020-06-22 10:07
 **/
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

    @ExceptionHandler(SocketActuatorException.class)
    public ResultVO handler(SocketActuatorException ex){
        return ResultVO.failure(ex.getError());
    }
}
