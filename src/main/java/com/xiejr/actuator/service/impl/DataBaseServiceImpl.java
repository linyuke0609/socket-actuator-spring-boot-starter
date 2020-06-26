package com.xiejr.actuator.service.impl;

import com.baomidou.mybatisplus.generator.config.IDbQuery;
import com.xiejr.actuator.entity.TableInfo;
import com.xiejr.actuator.entity.vo.SqlFieldInfo;
import com.xiejr.actuator.enums.DbType;
import com.xiejr.actuator.exceptioin.SocketActuatorException;
import com.xiejr.actuator.service.DataBaseService;
import com.xiejr.actuator.util.DbTypeUtil;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.annotation.DbType.ORACLE;
import static com.baomidou.mybatisplus.annotation.DbType.POSTGRE_SQL;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 数据库服务接口实现类
 * @author: xjr
 * @create: 2020-06-18 18:23
 **/
@Service("dataBaseService")
public class DataBaseServiceImpl implements DataBaseService {

    @Value("${spring.datasource.driver-class-name:}")
    private String driverClassName;

    private DataSource dataSource;


    public DataBaseServiceImpl(@Autowired(required = false) DataSource dataSource){
        this.dataSource=dataSource;
    }


    @Override
    public List<TableInfo> tables() throws SQLException {
        checkDataSource();
        @Cleanup Connection connection = dataSource.getConnection();
        DbType dbType = DbTypeUtil.getDbTypeByDriverClassName(driverClassName, connection.getClass().getName());
        if(dbType == null) {
            throw new SocketActuatorException("请在配置文件中设置 spring.datasource.driver-class-name");
        }
        IDbQuery dbQuery =dbType.getiDbQuery();
        String tablesSql = dbQuery.tablesSql();
        String schema = connection.getMetaData().getSchemaTerm();
        if (POSTGRE_SQL == dbQuery.dbType()) {
            tablesSql = String.format(tablesSql, schema);
        }

        if (ORACLE == dbQuery.dbType()) {
            tablesSql = String.format(tablesSql, schema);
        }
        PreparedStatement preparedStatement = connection.prepareStatement(tablesSql);
        ResultSet results = preparedStatement.executeQuery();
        List<TableInfo> tableInfos = new ArrayList<>();
        while (results.next()) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName( results.getString(dbQuery.tableName()));
            tableInfo.setComment( results.getString(dbQuery.tableComment()));
            tableInfos.add(tableInfo);
        }
        return tableInfos;

    }

    @Override
    public List<SqlFieldInfo> getFields(String tableName) throws SQLException {
        checkDataSource();
        @Cleanup Connection connection = dataSource.getConnection();
        DbType dbType = DbTypeUtil.getDbTypeByDriverClassName(driverClassName, connection.getClass().getName());
        if (dbType == null) {
            throw new SocketActuatorException("请在配置文件中设置 spring.datasource.driver-class-name");
        }
        IDbQuery dbQuery = dbType.getiDbQuery();

        String tableFieldsSql = dbQuery.tableFieldsSql();
        String schema = connection.getMetaData().getSchemaTerm();
        if (POSTGRE_SQL == dbQuery.dbType()) {
            tableFieldsSql = String.format(tableFieldsSql, schema, tableName);
        } else if (ORACLE == dbQuery.dbType()) {
            tableFieldsSql = String.format(tableFieldsSql.replace("#schema", schema), tableName);
        } else {
            tableFieldsSql = String.format(tableFieldsSql, tableName);
        }

        PreparedStatement preparedStatement = connection.prepareStatement(tableFieldsSql);
        ResultSet results = preparedStatement.executeQuery();

        List<SqlFieldInfo> sqlFieldInfos = new ArrayList<>();
        while (results.next()) {
            SqlFieldInfo sqlFieldInfo = new SqlFieldInfo();
            sqlFieldInfo.setFieldName(results.getString(dbQuery.fieldName()));
            sqlFieldInfo.setDataType(results.getString(dbQuery.fieldType()));
            sqlFieldInfo.setComment(results.getString(dbQuery.fieldComment()));
            sqlFieldInfos.add(sqlFieldInfo);

        }
        return sqlFieldInfos;
    }

    public void checkDataSource() throws SocketActuatorException{
        if (this.dataSource==null){
            throw new SocketActuatorException("当前项目没有配置数据源");
        }
    }


}
