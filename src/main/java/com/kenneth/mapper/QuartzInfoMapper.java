package com.kenneth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.MyMapper;
import com.kenneth.domain.QuartzInfo;

public interface QuartzInfoMapper extends MyMapper<QuartzInfo> {

    QuartzInfo findByCode(String code);

    List<QuartzInfo> findList(Map<String, Object> result);

    void updateSelective(Map<String, Object> qiData);

    QuartzInfo findByQuartzId(@Param("id")Long id, @Param("state")Byte state);
}