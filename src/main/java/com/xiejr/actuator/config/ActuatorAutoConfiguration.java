package com.xiejr.actuator.config;

import com.xiejr.actuator.model.ActuatorBaseInfo;
import com.xiejr.actuator.model.HeartCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import store.sophi.xjr.annotations.EnableAiFace;


/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 计时计分自动装配类
 * @author: xjr
 * @create: 2020-06-16 15:35
 **/
@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "socket.actuator",name = "active",havingValue = "true")
@Slf4j
@EnableConfigurationProperties({ActuatorBaseInfo.class, HeartCheck.class})
@AutoConfigureAfter({ActuatorBaseInfo.class,HeartCheck.class})
@Import(ActuatorUIConfiguration.class)
@ComponentScan("com.xiejr.actuator")
@ServletComponentScan
@EnableAiFace(appId = "19500780",apiKey = "PNPvm88OxksbNN53WBuF7Khv",secretKey = "GDIhOqqtoplhP8Y6kjaNWHCvyMuUnjYF")
public class ActuatorAutoConfiguration  implements InitializingBean {

    @Autowired
    ActuatorBaseInfo actuatorBaseInfo;



    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("socket-actuator system starts");
        log.info("监控系统信息:{}",actuatorBaseInfo.toString());
    }

    /*
  开启websocket支持
   */
    @Bean("endPoint")
    @ConditionalOnMissingBean(ServerEndpointExporter.class)
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
