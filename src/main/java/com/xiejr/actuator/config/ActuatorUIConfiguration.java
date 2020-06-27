package com.xiejr.actuator.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 监控UI界面配置路径拦截
 * @author: xjr
 * @create: 2020-06-16 17:05
 **/
public class ActuatorUIConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("monitor.html").addResourceLocations("classpath:/META-INF/actuator-ui/");
        registry.addResourceHandler("/monitor/**").addResourceLocations("classpath:/META-INF/actuator-ui/monitor/");
    }
}
