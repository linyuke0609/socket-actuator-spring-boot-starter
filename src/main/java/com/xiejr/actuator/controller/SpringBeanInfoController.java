package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.SpringBeanInfo;
import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.model.ResultVO;
import com.xiejr.actuator.service.SpringBeanInfoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: spring bean信息web接口
 * @author: xjr
 * @create: 2020-06-18 15:04
 **/
@RestController
@ApiIgnore
@RequestMapping(ConstantConfig.PREFIX+"/spring")
@Lazy
public class SpringBeanInfoController {

    private SpringBeanInfoService springBeanInfoService;

    public SpringBeanInfoController(@Lazy SpringBeanInfoService springBeanInfoService){
        this.springBeanInfoService=springBeanInfoService;
    }

    @GetMapping("/getBeans")
    public ResultVO getSpringBeans(){
        return ResultVO.<List<SpringBeanInfo>>data(springBeanInfoService.listBeans());
    }


    @GetMapping("/getProperties")
    public ResultVO getProperties(){
        return ResultVO.<Map<String,Object>>data(springBeanInfoService.getProperties());
    }
}
