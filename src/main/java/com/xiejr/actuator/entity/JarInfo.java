package com.xiejr.actuator.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: jar包信息
 * @author: xjr
 * @create: 2020-06-18 17:45
 **/
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JarInfo extends BaseEntity {

    /**
     * jar包路径
     */
    private String jarPath;

    /**
     * jar包名称
     */
    public String getJarName() {
        if(jarPath != null) {
            String[] split = jarPath.split("\\\\");
            if(split.length > 0) {
                return split[split.length - 1];
            }
        }
        return null;
    }
}
