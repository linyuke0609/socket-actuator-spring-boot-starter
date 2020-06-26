package com.xiejr.actuator.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiejr.actuator.entity.ClassInfo;
import com.xiejr.actuator.entity.JarInfo;
import com.xiejr.actuator.entity.JvmInfo;
import com.xiejr.actuator.enums.JvmEnum;
import com.xiejr.actuator.service.JvmService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: jvm虚拟机服务接口实现类
 * @author: xjr
 * @create: 2020-06-17 14:31
 **/
@Service("JvmService")
@Lazy
public class JvmServiceImpl implements JvmService {
    @Override
    public JvmInfo getRuntimeInfo() {
        JSONObject operatingSystem=JSON.parseObject(JSON.toJSONString(ManagementFactory.getOperatingSystemMXBean()));
        JvmInfo jvmInfo=JvmInfo.builder().cpuNums(operatingSystem.getInteger(JvmEnum.AVAILABLE_PROCESSORS.getCode()))
                .cpuUseAge(operatingSystem.getFloat(JvmEnum.SYSTEM_CPU_LOAD.getCode()))
                .memoryFreeBytes(operatingSystem.getLong(JvmEnum.FREE_PHYSICAL_MEMORY_SIZE.getCode()))
                .memoryTotalBytes(operatingSystem.getLong(JvmEnum.TOTALP_HYSICAL_MEMORYS_IZE.getCode()))
                .osName(operatingSystem.getString(JvmEnum.OS_NAME.getCode())).build();
        return jvmInfo;
    }

    @Override
    public List<ClassInfo> getClasses() {
        List<ClassInfo> list = new ArrayList<>();
        ClassLoader classLoader = this.getClass().getClassLoader();


        while (classLoader != null) {
            Vector<Class> classes = (Vector<Class>)getPropertyByFiledName(classLoader, ClassLoader.class, JvmEnum.CLASSLOADER_CLASSES.getCode());
            Enumeration<Class> elements = classes.elements();

            String classLoaderName = classLoader.getClass().getSimpleName();
            while (elements.hasMoreElements()) {
                Class clazz = elements.nextElement();
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassLoaderName(classLoaderName);
                classInfo.setClassName(clazz.getName());
                classInfo.setSimpleClassName(clazz.getSimpleName());
                list.add(classInfo);
            }


            classLoader = classLoader.getParent();

        }

        return list;
    }

    @Override
    public List<JarInfo> getJarList() {
        List<JarInfo> jarInfos = new ArrayList<>();
        String jarPathStrings = System.getProperty(JvmEnum.JAVA_CLASS_PATH.getCode());
        String[] jarPaths = jarPathStrings.split(";");
        for (String jarPath:jarPaths) {
            if(jarPath.toUpperCase().endsWith(JvmEnum.JAR_TYPE.getCode())) {
                JarInfo jarInfo = new JarInfo();
                jarInfo.setJarPath(jarPath);
                jarInfos.add(jarInfo);
            }

        }
        return jarInfos;
    }

    /**
     * 通过字段名获取属性
     *
     * @param obj       源
     * @param clazz     类
     * @param filedName 字段名
     * @return
     */
    public static Object getPropertyByFiledName(Object obj, Class clazz, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
