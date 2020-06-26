package com.xiejr.actuator.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: spring bean信息
 * @author: xjr
 * @create: 2020-06-18 14:08
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpringBeanInfo extends BaseEntity {
    private String beanName;

    private String beanClassName;

    private String simpleClassName;

    private boolean lazy;

    private boolean singleton;


}
