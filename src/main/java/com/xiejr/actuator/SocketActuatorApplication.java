package com.xiejr.actuator;

import com.gitee.sophis.annotations.EnableApiDocument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description:
 * @author: xjr
 * @create: 2020-06-26 16:08
 **/
@SpringBootApplication
@EnableApiDocument(basePackages = "com.xiejr",groupName = "test",version = "1.0")
public class SocketActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocketActuatorApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
