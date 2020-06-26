package com.xiejr.actuator.service;

import com.xiejr.actuator.entity.DeviceVo;
import com.xiejr.actuator.exceptioin.SocketActuatorException;
import com.xiejr.actuator.model.Message;

import java.util.List;
import java.util.Map;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: ipad设备监控服务
 * @author: xjr
 * @create: 2020-06-23 15:13
 **/
public interface IpadActuatorService {

    List<DeviceVo> list();

    void disconnect(String sessionId) throws SocketActuatorException;

    Map<String, Message> hearts(String sessionId) throws SocketActuatorException;
}
