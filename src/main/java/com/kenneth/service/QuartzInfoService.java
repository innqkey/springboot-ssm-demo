package com.kenneth.service;

import java.util.List;
import java.util.Map;

import com.kenneth.domain.QuartzInfo;

/**
 * @author qinkai
 * @date 2018年8月14日
 */
public interface QuartzInfoService {

    QuartzInfo findByCode(String string);

    List<QuartzInfo> findList(Map<String, Object> paramMap);

    void updateByMap(Map<String, Object> qiData);

    QuartzInfo findByQuartzId(Long id);

    String addAndUpdate(QuartzInfo quartz);

}
