package com.xiejr.actuator.service;

import com.xiejr.actuator.entity.DeviceVo;
import com.xiejr.actuator.exceptioin.SocketActuatorException;

import java.util.List;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: ipad设备监控服务
 * @author: xjr
 * @create: 2020-06-23 15:13
 **/
public interface IpadActuatorService {

    List<DeviceVo> list();

    void disconnect(String sessionId) throws SocketActuatorException;
}
