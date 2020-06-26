package com.xiejr.actuator.service.impl;

import com.xiejr.actuator.entity.SpringBeanInfo;
import com.xiejr.actuator.service.SpringBeanInfoService;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: spring bean接口服务实现类
 * @author: xjr
 * @create: 2020-06-18 14:20
 **/
@Service("springBeanService")
@Lazy
public class SpringBeanInfoServiceImpl implements SpringBeanInfoService {

    private StandardEnvironment environment;

    private DefaultListableBeanFactory beanFactory;

    public SpringBeanInfoServiceImpl(StandardEnvironment environment,@Autowired(required = false) DefaultListableBeanFactory beanFactory){
        this.environment=environment;
        this.beanFactory=beanFactory;
    }

    @Override
    public List<SpringBeanInfo> listBeans() {
        Iterator<String> beanNameIterator=beanFactory.getBeanNamesIterator();
        List<SpringBeanInfo> result=new ArrayList<>(100);
        while (beanNameIterator.hasNext()){
            String beanName=beanNameIterator.next();
            BeanDefinition beanDefinition=null;
            try {
                beanDefinition=beanFactory.getBeanDefinition(beanName);;
            }catch (NoSuchBeanDefinitionException ex){
                continue;
            }
            String className=beanDefinition.getBeanClassName();
            SpringBeanInfo beanInfo=SpringBeanInfo.builder()
                    .beanName(beanName)
                    .lazy(beanDefinition.isLazyInit())
                    .simpleClassName(className!=null?className.substring(className.lastIndexOf(".")+1):"")
                    .beanClassName(className)
                    .singleton(beanDefinition.isSingleton())
                    .build();
            result.add(beanInfo);
        }
        return result;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> result = new HashMap<>(32);
        MutablePropertySources propertySources = environment.getPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();

        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            Object source = next.getSource();
            if (source instanceof Map) {
                Map<String, Object> map = (Map) source;
                Set<String> keySet = map.keySet();
                for (String key : keySet) {
                    Object value = map.get(key);
                    result.put(key, value.toString());
                }
            }
        }
        Set<String> keys = System.getProperties().stringPropertyNames();
        for (String key: keys) {
            result.remove(key);
        }
        return result;
    }
}
