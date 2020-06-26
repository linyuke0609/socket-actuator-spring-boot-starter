package com.xiejr.actuator.entity.vo;

import com.xiejr.actuator.entity.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 表sql字段视图对象
 * @author: xjr
 * @create: 2020-06-23 16:08
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlFieldInfo extends BaseEntity {

    private String fieldName;

    private String comment;

    private String dataType;

    private boolean uniqueKey;
}
