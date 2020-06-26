package com.xiejr.actuator.controller;

import com.xiejr.actuator.entity.SimpleQuartzBean;
import com.xiejr.actuator.entity.constant.ConstantConfig;
import com.xiejr.actuator.exceptioin.SocketActuatorException;
import com.xiejr.actuator.model.ResultVO;
import com.xiejr.actuator.util.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 定时器web接口
 * @author: xjr
 * @create: 2020-06-24 16:13
 **/
@RestController
@ApiIgnore
@RequestMapping(ConstantConfig.PREFIX+"/quartz")
@Lazy
public class QuartzController {

    private Scheduler scheduler;

    public QuartzController(@Lazy Scheduler scheduler){
        this.scheduler=scheduler;
    }

    @GetMapping("/listAll")
    public ResultVO listAll(){
        List<SimpleQuartzBean> res=new ArrayList<>(QuartzUtils.jobMap.size());
        QuartzUtils.jobMap.forEach((key,value)->{
            res.add(value);
        });
        return ResultVO.<List<SimpleQuartzBean>>data(res);
    }

    @PostMapping("/create")
    public ResultVO createJob(@RequestBody SimpleQuartzBean simpleQuartzBean) throws SocketActuatorException {
        QuartzUtils.createScheduleJob(scheduler,simpleQuartzBean);
        return ResultVO.<String>success("create successfully");
    }

    @PutMapping("/pause/{jobKey}")
    public ResultVO pause(@PathVariable String jobKey) throws  SocketActuatorException{
        QuartzUtils.pauseScheduleJob(scheduler,jobKey);
        return ResultVO.<String>success("pause successfully");
    }

    @PostMapping("/forceRunTaskOnce/{jobKey}")
    public ResultVO forceRunTaskOnce(@PathVariable String jobKey){
        QuartzUtils.runOnce(scheduler,jobKey);
        return ResultVO.<String>success("run successfully");
    }

    @PostMapping("/resumeTask/{jobKey}")
    public ResultVO resumeTask(@PathVariable String jobKey){
        QuartzUtils.resumeScheduleJob(scheduler,jobKey);
        return ResultVO.<String>success("resume successfully");
    }

    @PutMapping("/resumeTask}")
    public ResultVO update(@RequestBody SimpleQuartzBean simpleQuartzBean){
        QuartzUtils.updateScheduleJob(scheduler,simpleQuartzBean);
        return ResultVO.<String>success("update successfully");
    }

}
