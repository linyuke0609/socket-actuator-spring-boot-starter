package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.model.ActuatorBaseInfo;
import com.xiejr.actuator.model.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 系统基本信息web接口
 * @author: xjr
 * @create: 2020-06-18 10:41
 **/
@RestController
@Lazy
@ApiIgnore
@RequestMapping(ConstantConfig.PREFIX+"/base")
public class BaseInfoController {

    @Autowired(required = false)
    @Lazy
    ActuatorBaseInfo actuatorBaseInfo;

    @GetMapping("/getSysInfo")
    public ResultVO getSysInfo(){
        return ResultVO.<ActuatorBaseInfo>data(actuatorBaseInfo);
    }


}
