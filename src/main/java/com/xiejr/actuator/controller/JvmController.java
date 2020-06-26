package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.ClassInfo;
import com.xiejr.actuator.entity.JarInfo;
import com.xiejr.actuator.entity.JvmInfo;
import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.model.ResultVO;
import com.xiejr.actuator.service.JvmService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: java虚拟机web接口
 * @author: xjr
 * @create: 2020-06-17 15:09
 **/
@RestController
@Lazy
@ApiIgnore
@RequestMapping(ConstantConfig.PREFIX+"/jvm")
public class JvmController {

    private  JvmService jvmService;

    public JvmController(@Lazy JvmService jvmService){
        this.jvmService=jvmService;
    }

    @GetMapping("runtime-info")
    public ResultVO getRuntimeInfo(){
        return ResultVO.<JvmInfo>data(jvmService.getRuntimeInfo());
    }

    @GetMapping("getProperties")
    public ResultVO getProperties(){
        return ResultVO.<Map<Object,Object>>data(System.getProperties());
    }

    @GetMapping("getClasses")
    public ResultVO getClasses(){
        return ResultVO.<List<ClassInfo>>data(jvmService.getClasses());
    }

    @GetMapping("getJarList")
    public ResultVO getJarList(){
        return ResultVO.<List<JarInfo>>data(jvmService.getJarList());
    }
}
