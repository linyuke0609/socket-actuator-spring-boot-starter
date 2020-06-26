package com.xiejr.actuator.enums;

import lombok.Getter;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 角色枚举
 * @author: xjr
 * @create: 2020-06-23 11:58
 **/
@Getter
public enum  RoleEnum {

    SERVER("server","服务端"),

    CLIENT("client","客户端");


    private String code;

    private String value;

    RoleEnum(String code,String value){
        this.code=code;
        this.value=value;
    }
}
