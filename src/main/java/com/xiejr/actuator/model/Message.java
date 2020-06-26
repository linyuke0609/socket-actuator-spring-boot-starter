package com.xiejr.actuator.model;

import com.xiejr.actuator.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: socket传输数据格式
 * @author: xjr
 * @create: 2020-06-23 11:28
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Message extends BaseEntity {
    private String from;

    private String to;

    private String sendTime;

    private String content;


}
