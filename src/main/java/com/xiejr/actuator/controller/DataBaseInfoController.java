package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.entity.vo.SqlFieldInfo;
import com.xiejr.actuator.model.ResultVO;
import com.xiejr.actuator.service.DataBaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 数据库服务web接口
 * @author: xjr
 * @create: 2020-06-18 18:19
 **/
@ApiIgnore
@RestController
@Lazy
@RequestMapping(ConstantConfig.PREFIX+"/database")
public class DataBaseInfoController {

    private DataBaseService dataBaseService;

    public DataBaseInfoController(@Lazy DataBaseService dataBaseService){
        this.dataBaseService=dataBaseService;
    }

    @GetMapping("/tables")
    public ResultVO tables() throws SQLException {
        return ResultVO.data(dataBaseService.tables());
    }

    @GetMapping("/fields")
    public ResultVO fields(String tableName) throws Exception{
        return ResultVO.<List<SqlFieldInfo>>data(dataBaseService.getFields(tableName));
    }
}
