package com.xiejr.actuator.enums;

import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.baomidou.mybatisplus.generator.config.querys.*;

import java.util.Arrays;
import java.util.List;

/**
 * @description 数据库类型枚举
 * @author: xiejiarong
 * @create: 2020/06/22
 **/
public enum DbType {

    /**
     * mysql数据量
     */
    Mysql(Arrays.asList("com.mysql.jdbc.Driver"), new MySqlQuery(), "MySql"),
    /**
     * oracle数据库
     */
    Oracle(Arrays.asList("oracle.jdbc.OracleDriver"), new OracleQuery(), "Oracle"),

    /**
     * db2数据库
     */
    DB2(Arrays.asList("com.ibm.db2.jcc.DB2Driver"), new DB2Query(), "DB2"),

    /**
     * postgre数据库
     */
    PostgreSql(Arrays.asList("org.postgresql.Driver"), new PostgreSqlQuery(), "Postgre"),

    /**
     * MariaDB数据库
     */
    Mariadb(Arrays.asList("org.mariadb.jdbc.Driver"), new MariadbQuery(), "MariaDB"),

    /**
     * sqlserver数据库
     */
    SqlServer(Arrays.asList("com.microsoft.sqlserver.jdbc.SQLServerDriver"), new SqlServerQuery(), "SQLServer");

    /**
     * 驱动列表
     */
    private final List<String> driverList;

    /**
     * 查询适配器
     */
    private final IDbQuery iDbQuery;

    /**
     * 描述
     */
    private final String desc;

    private DbType(List<String> driverList, IDbQuery iDbQuery, String desc) {
        this.driverList = driverList;
        this.iDbQuery = iDbQuery;
        this.desc = desc;
    }

    public List<String> getDriverList() {
        return driverList;
    }

    public IDbQuery getiDbQuery() {
        return iDbQuery;
    }

    public String getDesc() {
        return desc;
    }
}
