package com.xiejr.actuator.util;


import com.xiejr.actuator.entity.SimpleQuartzBean;
import com.xiejr.actuator.exceptioin.SocketActuatorException;
import org.quartz.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: 定时任务工具类
 * @author: xjr
 * @create: 2020-06-24 15:51
 **/

public class QuartzUtils {

    public static Map<String,SimpleQuartzBean> jobMap=new ConcurrentHashMap<>(20);

    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     * @param scheduler   调度器
     * @param quartzBean  定时任务信息类
     */
    public static void createScheduleJob(Scheduler scheduler, SimpleQuartzBean quartzBean) throws SocketActuatorException {
        try {
            quartzBean.setStatus(1);
            //获取到定时任务的执行类  必须是类的绝对路径名称
            //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzBean.getJobClass());
            // 构建定时任务信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(quartzBean.getJobName()).build();
            // 设置定时任务执行方式
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(quartzBean.getEvictionIntervalTimerInMs())
                    .repeatForever();
            // 构建触发器trigger
            SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzBean.getJobName(),quartzBean.getGroupName()).withSchedule(simpleScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            jobMap.put(quartzBean.getJobName(),quartzBean);
        } catch (ClassNotFoundException e) {
            throw new SocketActuatorException("定时任务类路径出错：请输入类的绝对路径");
        } catch (SchedulerException e) {
           throw new SocketActuatorException("创建定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称暂停定时任务
     * @param scheduler  调度器
     * @param jobName    定时任务名称
     */
    public static void pauseScheduleJob(Scheduler scheduler, String jobName) throws SocketActuatorException{
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.pauseJob(jobKey);
            jobMap.get(jobName).setStatus(0);
        } catch (SchedulerException e) {
            throw new SocketActuatorException("暂停定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称恢复定时任务
     * @param scheduler  调度器
     * @param jobName    定时任务名称
     */
    public static void resumeScheduleJob(Scheduler scheduler, String jobName) throws SocketActuatorException{
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.resumeJob(jobKey);
            jobMap.get(jobName).setStatus(1);
        } catch (SchedulerException e) {
            throw new SocketActuatorException("启动定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据任务名称立即运行一次定时任务
     * @param scheduler     调度器
     * @param jobName       定时任务名称
     */
    public static void runOnce(Scheduler scheduler, String jobName) throws SocketActuatorException{
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.triggerJob(jobKey);
            jobMap.get(jobName).setStatus(1);
        } catch (SchedulerException e) {
            throw new SocketActuatorException("运行定时任务出错："+e.getMessage());
        }
    }

    /**
     * 更新定时任务
     * @param scheduler   调度器
     * @param quartzBean  定时任务信息类
     * @throws SchedulerException
     */
    public static void updateScheduleJob(Scheduler scheduler, SimpleQuartzBean quartzBean)  throws SocketActuatorException{
        try {
            //获取到对应任务的触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzBean.getJobName());
            //设置定时任务执行方式
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(quartzBean.getEvictionIntervalTimerInMs())
                    .repeatForever();
            //重新构建任务的触发器trigger
            SimpleTrigger trigger = (SimpleTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
            jobMap.put(quartzBean.getJobName(),quartzBean);
        } catch (SchedulerException e) {
            throw new SocketActuatorException("更新定时任务出错："+e.getMessage());
        }
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     * @param scheduler 调度器
     * @param jobName   定时任务名称
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName) throws SocketActuatorException{
        JobKey jobKey = JobKey.jobKey(jobName);
        try {
            scheduler.deleteJob(jobKey);
            jobMap.remove(jobName);
        } catch (SchedulerException e) {
           throw new SocketActuatorException("删除定时任务出错："+e.getMessage());
        }
    }
}
