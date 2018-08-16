package com.common;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

/**
 * 定时任务动态修改
 * 
 * @author Administrator
 * 
 */
public class QuartzManager {

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
    public static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    /**
     * 添加一个定时任务，使用默认的组名，任务名，触发器名
     * 
     * @param jobName
     * @param cls
     * @param time
     */
    public static void addJob(String jobName, Class cls, String time) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            // 构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, TRIGGER_GROUP_NAME).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();
            // 交由Scheduler安排触发
            sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    /**
     * 添加一个定时任务
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param jobClass
     * @param time
     */
    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
            Class cls, String time) {

        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            // 构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(jobGroupName, triggerGroupName).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();
            // 交由Scheduler安排触发
            sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            logger.error("", e);
        }

    }
    /**
     * 停止一个任务(使用默认的任务组名，触发器名，触发器组名)
     * 
     * @param jobName
     */
    public static void pauseJob(String jobName) {

        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(jobName,TRIGGER_GROUP_NAME);
//            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);  
//            sched.pauseTrigger(triggerKey);
            // 停止任务
            sched.pauseJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("", e);
        }

    }
    /**
     * 恢复一个任务(使用默认的任务组名，触发器名，触发器组名)
     * 
     * @param jobName
     */
    public static void resumeJob(String jobName) {
        
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            // 恢复任务  
            JobKey jobKey = new JobKey(jobName,TRIGGER_GROUP_NAME);
            sched.resumeJob(jobKey);  
        } catch (SchedulerException e) {
            logger.error("", e);
        }
        
    }
    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * 
     * @param jobName
     * @param time
     */
    public static void modifyJobTime(String jobName, String time) {

//        try {
//            Scheduler sched = gSchedulerFactory.getScheduler();
//            CronTrigger trigger = (CronTrigger) sched.getTrigger(JOB_GROUP_NAME, TRIGGER_GROUP_NAME);
//            if (trigger == null) {
//                return;
//            }
//            String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//                JobDetail jobDetail = sched.getJobDetail(jobName, JOB_GROUP_NAME);
//                Class objJobClass = jobDetail.getJobClass();
//                removeJob(jobName);
//                addJob(jobName, objJobClass, time);
//            }
//        } catch (SchedulerException e) {
//            logger.error("", e);
//        }

    }

    /**
     * 修改一个任务的触发时间
     * 
     * @param triggerName
     * @param triggerGroupName
     * @param time
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {

//        try {
//            Scheduler sched = gSchedulerFactory.getScheduler();
//            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName, triggerGroupName);
//            if (trigger == null) {
//                return;
//            }
//            String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//                CronTrigger ct = (CronTrigger) trigger;
//                // 修改时间
//                ct.setCronExpression(time);
//                // 重启触发器
//                sched.resumeTrigger(triggerName, triggerGroupName);
//            }
//        } catch (SchedulerException | ParseException e) {
//            logger.error("", e);
//        }

    }

    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     * 
     * @param jobName
     */
    public static void removeJob(String jobName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);  
            // 停止触发器  
            sched.pauseTrigger(triggerKey);  
            // 移除触发器  
            sched.unscheduleJob(triggerKey);  
            // 删除任务  
            sched.deleteJob(JobKey.jobKey(jobName, TRIGGER_GROUP_NAME));
            logger.info("removeJob:" + JobKey.jobKey(jobName));
        } catch (SchedulerException e) {
            logger.error("", e);
        }

    }

    /**
     * 移除一个任务
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {

//        try {
//            Scheduler sched = gSchedulerFactory.getScheduler();
//            sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
//            sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
//            sched.deleteJob(jobName, jobGroupName);// 删除任务
//        } catch (SchedulerException e) {
//            logger.error("", e);
//        }

    }

    /**
     * 立即执行任务(使用默认的任务组名，触发器名，触发器组名)
     * 
     * @param jobName
     */
    public static void startJobNow(String jobName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobKey jobKey = new JobKey(jobName,TRIGGER_GROUP_NAME);
            sched.triggerJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("", e);
        }
    }

    /**
     * 立即执行任务
     * 
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public static void startJobNow(String jobName, String jobGroupName) {

//        try {
//            Scheduler sched = gSchedulerFactory.getScheduler();
//            sched.triggerJob(jobName, jobGroupName);
//        } catch (SchedulerException e) {
//            logger.error("", e);
//        }

    }

    /**
     * 启动所有定时任务
     */
    public static void startJobs() {

        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (SchedulerException e) {
            logger.error("", e);
        }

    }

    /**
     * 关闭所有定时任务
     */
    public static void shutdownJobs() {

        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (SchedulerException e) {
            logger.error("", e);
        }

    }

}
