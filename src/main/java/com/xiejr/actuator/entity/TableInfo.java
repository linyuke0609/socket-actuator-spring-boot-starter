package com.xiejr.actuator.entity;

import lombok.Data;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 数据库表信息实体
 * @author: xjr
 * @create: 2020-06-22 09:43
 **/
@Data
public class TableInfo {
    private String tableName;

    private String comment;

    private String tableSql;
}
