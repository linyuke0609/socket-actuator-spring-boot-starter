package com.xiejr.actuator.enums;

import lombok.Getter;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: java虚拟机枚举字段
 * @author: xjr
 * @create: 2020-06-17 14:33
 **/
@Getter
public enum JvmEnum {

    CLASSLOADER_CLASSES("classes","类加载器中的class"),

    AVAILABLE_PROCESSORS("availableProcessors","cpu内核数key"),

    FREE_PHYSICAL_MEMORY_SIZE("freePhysicalMemorySize","空闲内存大小key"),

    SYSTEM_CPU_LOAD("systemCpuLoad","cpu使用率"),

    TOTALP_HYSICAL_MEMORYS_IZE("totalPhysicalMemorySize","总物理内存大小"),

    OS_NAME("name","操作系统名key"),

    JAVA_CLASS_PATH("java.class.path","加载的jar包"),

    JAR_TYPE(".JAR","jar包文件结尾");





    private String code;

    private String msgZn;

    JvmEnum(String code,String msgZn){
        this.code=code;
        this.msgZn=msgZn;
    }



}
