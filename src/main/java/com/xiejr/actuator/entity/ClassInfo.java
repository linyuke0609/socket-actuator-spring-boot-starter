package com.xiejr.actuator.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: class信息实体
 * @author: xjr
 * @create: 2020-06-18 16:51
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassInfo extends BaseEntity {

    private String className;

    private String simpleClassName;

    private String classLoaderName;
}
