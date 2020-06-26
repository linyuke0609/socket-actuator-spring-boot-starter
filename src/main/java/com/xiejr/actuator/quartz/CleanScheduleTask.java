package com.xiejr.actuator.quartz;

import com.xiejr.actuator.model.HeartCheck;
import com.xiejr.actuator.socket.SocketClient;
import com.xiejr.actuator.socket.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 后台监控定时任务(剔除无用socket)
 * @author: xjr
 * @create: 2020-06-22 15:05
 **/
@Slf4j
public class CleanScheduleTask extends QuartzJobBean {

    @Autowired
    HeartCheck heartCheck;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("evit time-out socket in every {} seconds",heartCheck.getEvictionIntervalTimerInMs());
        LocalDateTime now=LocalDateTime.now();
        for (Iterator<Map.Entry<String, SocketClient>> it = WebSocketConfig.socketConfigMap.entrySet().iterator(); it.hasNext();){
            Map.Entry<String,SocketClient> item=it.next();
            LocalDateTime lastTime=item.getValue().getLastRefreshTime();
            Duration between=Duration.between(lastTime,now);
            if ((between.toMillis()/1000)>heartCheck.getLeaseExpirationDurationInSeconds()){
                if (heartCheck.isEvitWhenTimeOut()) {
                    it.remove();
                } else {
                    item.getValue().setClose(true);
                }
            }
        }
    }
}
