package com.xiejr.actuator.service.impl;

import com.xiejr.actuator.entity.DeviceVo;
import com.xiejr.actuator.exceptioin.SocketActuatorException;
import com.xiejr.actuator.service.IpadActuatorService;
import com.xiejr.actuator.socket.SocketClient;
import com.xiejr.actuator.socket.WebSocketConfig;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: ipad设备监控服务实现
 * @author: xjr
 * @create: 2020-06-23 15:15
 **/
@Service("ipadActuatorService")
public class IpadActuatorServiceImpl implements IpadActuatorService {

    private static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh时mm分ss秒");

    @Override
    public List<DeviceVo> list() {
        List<DeviceVo> res=new ArrayList<>(WebSocketConfig.socketConfigMap.size());
        WebSocketConfig.socketConfigMap.forEach((key,value)->{
           DeviceVo device=DeviceVo.builder()
                   .sessionId(key)
                   .close(value.getClose()?"断线":"正常")
                   .conectTime(dateTimeFormatter.format(value.getConectTime()))
                   .deviceIp(value.getDeviceIp())
                   .lastModiftTime(dateTimeFormatter.format(value.getLastRefreshTime()))
                   .build();
           res.add(device);
        });
        return res;
    }

    @Override
    public void disconnect(String sessionId) throws SocketActuatorException {
        Session session= Optional.<SocketClient>ofNullable(WebSocketConfig.socketConfigMap.get(sessionId))
                .orElseThrow(()->new SocketActuatorException("找不到该台连接的设备,请确认是否已经断线")).getSession();
        try {
            session.close();
        } catch (IOException e) {
            throw new SocketActuatorException(e.getMessage());
        }

        ;
    }
}
