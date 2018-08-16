package com.kenneth.job;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.AppCtxUtil;
import com.kenneth.domain.QuartzInfo;
import com.kenneth.domain.QuartzLog;
import com.kenneth.service.QuartzInfoService;
import com.kenneth.service.QuartzLogService;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/6.
 */
public abstract class BasicTask implements Job, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(BasicTask.class);

    protected abstract QuartzInfo getQuartzInfo();
    protected abstract void doService() throws Exception;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        QuartzInfoService quartzInfoService = AppCtxUtil.getApplicationContext().getBean(QuartzInfoService.class);
        QuartzLogService quartzLogService = AppCtxUtil.getApplicationContext().getBean(QuartzLogService.class);
        QuartzInfo info = getQuartzInfo();
        QuartzLog ql = new QuartzLog();
        Map<String, Object> qiData = new HashMap<>();
        try {
            ql.setQuartzId(info.getId());
            ql.setCreateTime(new Date());
            String remark = doAction(info.getName());
            ql.setTime(DateTime.now().getMillis() - ql.getCreateTime().getTime());
            ql.setResult("10");
            ql.setRemark(remark);
            qiData.put("id", info.getId());
            qiData.put("succeed", info.getSucceed() + 1);
        } catch (Exception e) {
            ql.setResult("20");
            qiData.put("fail", info.getFail() + 1);
            logger.error(info.getCode() + " failed , exception is ", e);
        } finally {
            quartzLogService.save(ql);
            quartzInfoService.updateByMap(qiData);
            
            System.out.println(info.getName());
            // 取得job详情
            JobDetail jobDetail = context.getJobDetail();
            // 取得job名称
            String jobName = jobDetail.getClass().getName();
            logger.info("job名: " + jobDetail.getClass().getSimpleName());
            // 取得job的类
            logger.info("job类: " + jobDetail.getJobClass());
            // 取得job开始时间
            logger.info("job开始时间: " + context.getFireTime());
            // 取得job下次触发时间
            logger.info("job下次触发时间: " + context.getNextFireTime());

//            JobDataMap dataMap = jobDetail.getJobDataMap();
//            logger.info("itstyle: " + dataMap.getString("itstyle"));
//            logger.info("blog: " + dataMap.getString("blog"));
//            String[] array = (String[]) dataMap.get("data");
//            for (String str : array) {
//                logger.info("data: " + str);
//            }
        }
    }

    protected String doAction(String name) throws Exception {
        int succeed = 0;
        int fail = 0;
        int total = 0;
        try {
            doService();
            succeed++;
        } catch (Exception e) {
            logger.error(name + " error:", e);
            fail++;
        }
        total++;
        String remark = name + "任务:" + total + ",成功" + succeed + "次,失败" + fail + "次";
        return remark;
    }

}
