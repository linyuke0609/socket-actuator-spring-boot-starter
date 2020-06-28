package com.xiejr.actuator.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 设备信息视图对象
 * @author: xjr
 * @create: 2020-06-23 15:43
 **/
@Data
@Accessors(chain  =true)
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVo extends BaseEntity {

    private String sessionId;

    private String deviceIp;

    private String conectTime;

    private String lastModifyTime;

    private String close;
}
