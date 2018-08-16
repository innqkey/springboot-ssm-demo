package com.kenneth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.QuartzManager;
import com.common.ResUtils;
import com.kenneth.domain.QuartzInfo;
import com.kenneth.service.QuartzInfoService;

@RestController
@RequestMapping("/job")
public class JobController {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private QuartzInfoService quartzInfoService;

    @PostMapping("/add")
    public String save(QuartzInfo  quartz) {
        LOGGER.info("新增/修改任务");
        try {
            return quartzInfoService.addAndUpdate(quartz);
        } catch (Exception e) {
            e.printStackTrace();
            return ResUtils.execRes();
        }
    }
    // @PostMapping("/list")
    // public Result list(QuartzEntity quartz,Integer pageNo,Integer pageSize){
    // LOGGER.info("任务列表");
    // List<QuartzEntity> list = jobService.listQuartzEntity(quartz, pageNo,
    // pageSize);
    // return Result.ok(list);
    // }
    // @PostMapping("/trigger")
    // public Result trigger(QuartzEntity quartz,HttpServletResponse response) {
    // LOGGER.info("触发任务");
    // try {
    // JobKey key = new JobKey(quartz.getJobName(),quartz.getJobGroup());
    // scheduler.triggerJob(key);
    // } catch (SchedulerException e) {
    // e.printStackTrace();
    // return Result.error();
    // }
    // return Result.ok();
    // }
    @GetMapping("/pause")
    public String pause(@RequestParam(value = "id", required = true) Long id) {
        LOGGER.info("停止任务");
        try {
            QuartzInfo quartzInfo = quartzInfoService.findByQuartzId(id);
            if (null == quartzInfo) {
                return ResUtils.execRes("停止的定时任务不存在");
            }
            QuartzManager.pauseJob(quartzInfo.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResUtils.execRes();
        }
        return ResUtils.okRes();
    }

    @GetMapping("/resume")
    public String resume(@RequestParam(value = "id", required = true) Long id) {
        LOGGER.info("恢复任务");
        try {
            QuartzInfo quartzInfo = quartzInfoService.findByQuartzId(id);
            if (null == quartzInfo) {
                return ResUtils.execRes("恢复的定时任务不存在");
            }
            QuartzManager.resumeJob(quartzInfo.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResUtils.execRes();
        }
        return ResUtils.okRes();
    }

    @GetMapping("/remove")
    public String remove(@RequestParam(value = "id", required = true) Long id) {
        LOGGER.info("移除任务");
        try {
            QuartzInfo quartzInfo = quartzInfoService.findByQuartzId(id);
            if (null == quartzInfo) {
                return ResUtils.execRes("恢复的定时任务不存在");
            }
            QuartzManager.removeJob(quartzInfo.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResUtils.execRes();
        }
        return ResUtils.okRes();
    }
    
    @GetMapping("/startQuartz")
    public String startQuartz(@RequestParam(value = "id", required = true) Long id){
        try {
            QuartzInfo quartzInfo = quartzInfoService.findByQuartzId(id);
            if (null == quartzInfo) {
                return ResUtils.execRes("立即启动的定时任务不存在");
            }
            QuartzManager.startJobNow(quartzInfo.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            return ResUtils.execRes();
        }
        return ResUtils.okRes();
    }
}
