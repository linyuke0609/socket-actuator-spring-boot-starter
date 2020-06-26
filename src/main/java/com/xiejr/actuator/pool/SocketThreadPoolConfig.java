package com.xiejr.actuator.pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 自定义线程池
 * @author: xjr
 * @create: 2020-06-25 15:28
 **/
@Configuration("socketThreadPoolConfig")
@EnableAsync
public class SocketThreadPoolConfig {

    @Bean("socketThreadPool")
    public Executor socketThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
        //核心线程池大小
        threadPoolTaskExecutor.setCorePoolSize(5);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //队列容量
        threadPoolTaskExecutor.setQueueCapacity(5);
        //活跃时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //设置核心线程池回收机制
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //线程名字前缀
        threadPoolTaskExecutor.setThreadNamePrefix("socketActuator-");
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;

    }
}
