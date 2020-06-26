package com.xiejr.actuator.service;

import com.xiejr.actuator.entity.TableInfo;
import com.xiejr.actuator.entity.vo.SqlFieldInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 数据库服务接口
 * @author: xjr
 * @create: 2020-06-18 18:23
 **/
public interface DataBaseService {
    /**
     * 获取数据表信息
     * @return 表list
     * @author xiejiarong
     * @date 2020年06月22日 9:49
     */
    List<TableInfo> tables() throws SQLException;

    /**
     * 获取表格字段
     * @param tableName 表名
     * @throws  SQLException
     * @return List<SqlFieldInfo
     */
    List<SqlFieldInfo> getFields(String tableName) throws SQLException;
}
