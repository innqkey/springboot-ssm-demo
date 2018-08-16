package com.kenneth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenneth.domain.QuartzLog;
import com.kenneth.mapper.QuartzLogMapper;
import com.kenneth.service.QuartzLogService;

/**
 * @author qinkai
 * @date 2018年8月14日
 */
@Service
public class QuartzLogServiceImpl implements QuartzLogService{

    @Autowired
    private QuartzLogMapper quartzLogMapper;
    
    @Override
    public void save(QuartzLog ql) {
        quartzLogMapper.insert(ql);
    }

}
