package com.kenneth.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.QuartzManager;
import com.common.ResUtils;
import com.kenneth.domain.QuartzInfo;
import com.kenneth.mapper.QuartzInfoMapper;
import com.kenneth.service.QuartzInfoService;

/**
 * @author qinkai
 * @date 2018年8月14日
 */
@Service
public class QuartzInfoServiceImpl implements QuartzInfoService {
    @Autowired
    private QuartzInfoMapper quartzInfoMapper;

    @Override
    public QuartzInfo findByCode(String code) {
        return quartzInfoMapper.findByCode(code);
    }

    @Override
    public List<QuartzInfo> findList(Map<String, Object> result) {
        return quartzInfoMapper.findList(result);
    }

    @Override
    public void updateByMap(Map<String, Object> qiData) {
        quartzInfoMapper.updateSelective(qiData);
    }

    @Override
    public QuartzInfo findByQuartzId(Long id) {
        return quartzInfoMapper.findByQuartzId(id, new Byte("20"));
    }

    @Override
    public String addAndUpdate(QuartzInfo quartz) {
        // 修改，先删除旧任务，然后新加新任务;
        QuartzInfo result = quartzInfoMapper.findByQuartzId(quartz.getId(), new Byte("20"));
        if (quartz.getId() != null) {
            QuartzManager.removeJob(quartz.getCode());
            quartz.setUpdateTime(new Date());
            quartzInfoMapper.updateByPrimaryKeySelective(quartz);
        }
        try {
            QuartzManager.addJob(quartz.getCode(), Class.forName(result.getClassName()), quartz.getCycle());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return ResUtils.okRes();
        }
        return ResUtils.okRes();
    }

}
