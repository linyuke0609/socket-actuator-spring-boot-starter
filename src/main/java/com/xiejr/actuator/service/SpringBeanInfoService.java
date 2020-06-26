package com.xiejr.actuator.service;

import com.xiejr.actuator.entity.SpringBeanInfo;

import java.util.List;
import java.util.Map;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: spring bean接口服务
 * @author: xjr
 * @create: 2020-06-18 14:19
 **/
public interface SpringBeanInfoService {
    List<SpringBeanInfo> listBeans();

    Map<String,Object> getProperties();
}
