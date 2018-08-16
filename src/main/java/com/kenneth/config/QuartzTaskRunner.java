package com.kenneth.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.common.QuartzManager;
import com.kenneth.domain.QuartzInfo;
import com.kenneth.model.QuartzInfoModel;
import com.kenneth.service.QuartzInfoService;

/**
 * 初始化一个测试Demo任务 创建时间 2018年4月3日
 */
@Component
public class QuartzTaskRunner implements ApplicationRunner {

    private final static Logger logger = LoggerFactory.getLogger(QuartzTaskRunner.class);
    @Autowired
    private QuartzInfoService quartzInfoService;

    @Override
    public void run(ApplicationArguments var){
        logger.info("【启动所有任务】开始...");
        try {
            // 查询启用状态的定时任务信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("state", QuartzInfoModel.STATE_ENABLE);
            List<QuartzInfo> list = quartzInfoService.findList(paramMap);

            // 循环添加任务
            for (QuartzInfo quartzInfo : list) {
                QuartzManager.addJob(quartzInfo.getCode(), Class.forName(quartzInfo.getClassName()), quartzInfo.getCycle());
            }
            // 启动所有定时任务
            QuartzManager.startJobs();

        } catch (Exception e) {
            logger.error("启动定时任务异常--->", e);
        }
        logger.info("【启动所有任务】结束...");
        }

}