package com.xiejr.actuator.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 定时任务实体
 * @author: xjr
 * @create: 2020-06-24 15:46
 **/
@Data
@Accessors(chain = true)
public class SimpleQuartzBean {
    private String id;

    private String jobName;

    private String groupName;

    private String jobClass;

    private Integer status;

   private  Integer evictionIntervalTimerInMs;


}
