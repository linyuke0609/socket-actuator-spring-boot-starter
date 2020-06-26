package com.xiejr.actuator.service;

import com.xiejr.actuator.entity.ClassInfo;
import com.xiejr.actuator.entity.JarInfo;
import com.xiejr.actuator.entity.JvmInfo;

import java.util.List;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: java虚拟机服务接口
 * @author: xjr
 * @create: 2020-06-17 14:29
 **/
public interface JvmService {

    /**
     * 获取jvm运行状态
     *
     * @return JvmInfo
     */
    JvmInfo getRuntimeInfo();

    /**
     * 获取类加载器加载的class
     *
     * @return JvmInfo
     */
    List<ClassInfo> getClasses();

    /**
     * 获取加载到得jar
     *
     * @return JvmInfo
     */
    List<JarInfo> getJarList();
}
