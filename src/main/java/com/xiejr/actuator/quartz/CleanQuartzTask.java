package com.xiejr.actuator.quartz;

import com.xiejr.actuator.entity.SimpleQuartzBean;
import com.xiejr.actuator.init.abstracts.AbstractGlobalApplicationInit;
import com.xiejr.actuator.model.HeartCheck;
import com.xiejr.actuator.util.QuartzUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 清理废弃socket定时器
 * @author: xjr
 * @create: 2020-06-24 17:11
 **/
@Component("cleanQuartzTask")
public class CleanQuartzTask extends AbstractGlobalApplicationInit {
    @Autowired
    HeartCheck heartCheck;

    @Autowired
    Scheduler scheduler;

    @Override
    public void execute(ApplicationContext applicationContext, Environment environment, String beanName) throws Throwable {
        SimpleQuartzBean simpleQuartzBean=new SimpleQuartzBean();
        simpleQuartzBean.setEvictionIntervalTimerInMs(heartCheck.getEvictionIntervalTimerInMs());
        simpleQuartzBean.setId("1");
        simpleQuartzBean.setJobClass(CleanScheduleTask.class.getName());
        simpleQuartzBean.setJobName("cleanTask");
        simpleQuartzBean.setStatus(1);
        QuartzUtils.createScheduleJob(scheduler,simpleQuartzBean);
    }

    @Override
    public int order() {
        return 0;
    }
}
