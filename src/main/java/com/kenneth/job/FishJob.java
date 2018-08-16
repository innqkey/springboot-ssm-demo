package com.kenneth.job;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.AppCtxUtil;
import com.common.DateUtil;
import com.kenneth.domain.QuartzInfo;
import com.kenneth.service.QuartzInfoService;

import java.util.Date;
import java.util.List;

/**
 * Created by admin 13:32 2018/4/10
 */
public class FishJob extends BasicTask{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FishJob.class);

    @Override
    protected QuartzInfo getQuartzInfo() {
        QuartzInfoService quartzInfoService = AppCtxUtil.getApplicationContext().getBean(QuartzInfoService.class);
        QuartzInfo findByCode = quartzInfoService.findByCode("FishJob");
        return findByCode;
    }
    @Override
    protected void doService() throws Exception {
    }

    protected String doAction(String name) throws Exception {
//        RechargeOrderMapper rechargeOrderMapper = (RechargeOrderMapper)BeanUtil.getBean("rechargeOrderMapper");
//        List<RechargeOrder> orders = rechargeOrderMapper.selectOrderToTask(OrderProgressEunm.WAIT_ACTIVING.getType());
        int succeed = 10;
        int fail = 10;
        int total = 10;
//        if(orders != null && orders.size() > 0){
//            for(RechargeOrder order : orders){
//                try {
//                    int num = DateUtil.getDateFromNowNumber(0, new Date(), order.getExpireTime());
//                    if(num > 0){
//                        order.setState(OrderProgressEunm.ACTIVING_EXPIRE.getType());
//                        order.setUpdateTime(new Date());
//                        rechargeOrderMapper.updateByPrimaryKeySelective(order);
//                        succeed++;
//                        total++;
//                    }
//                }catch(Exception e){
//                    fail++;
//                    total++;
//                    logger.error("过期卡密定时任务失败，cardNo="+order.getCardNo(),e);
//                }
//            }
//        }else{
            logger.info("测试定时任务,日期:" + DateUtil.formatDate(new Date()));
//            return "过期卡密定时,总任务数为:0";
//        }
        String remark = "测试定时任务:" + total + ",成功" + succeed + "次,失败" + fail + "次";
        return  remark;
    }
}
