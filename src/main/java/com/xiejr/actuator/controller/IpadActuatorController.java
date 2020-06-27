package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.DeviceVo;
import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.exceptioin.SocketActuatorException;
import com.xiejr.actuator.model.Message;
import com.xiejr.actuator.model.ResultVO;
import com.xiejr.actuator.service.IpadActuatorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: ipad设备监控web服务
 * @author: xjr
 * @create: 2020-06-23 15:17
 **/
@RestController
@Lazy
@ApiIgnore
@RequestMapping(ConstantConfig.PREFIX+"/ipad-actuator")
public class IpadActuatorController {

    private IpadActuatorService ipadActuatorService;

    public IpadActuatorController(@Lazy IpadActuatorService ipadActuatorService){
        this.ipadActuatorService=ipadActuatorService;
    }

    @GetMapping("/listAll")
    public ResultVO listAll(){
        return ResultVO.<List<DeviceVo>>data(ipadActuatorService.list());
    }


    @PutMapping("/disconnect/{sessionId}")
    public ResultVO disconnect(@PathVariable("sessionId") String sessionId)throws SocketActuatorException {
        ipadActuatorService.disconnect(sessionId);
        return ResultVO.success("断开成功");
    }

    @GetMapping("/hearts/{sessionId}")
    public ResultVO hearts(@PathVariable("sessionId") String sessionId) throws SocketActuatorException{
        return ResultVO.<List<Message>>data(ipadActuatorService.hearts(sessionId));
    }


}