package com.kenneth.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenneth.config.PtpProducer;
import com.kenneth.domain.User;
import com.kenneth.mapper.UserMapper;
import com.kenneth.service.JmsConstant;

/**
 * 点对点模式 - 调用生产者
 * Created by jason-geng on 5/21/17.
 */
@RestController
@RequestMapping(value = "/ptp")
public class PtpController {

    @Autowired
    private PtpProducer ptpProducer;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/send")
    public Object send(){
        for(int i = 0; i < 5; i++){
            ptpProducer.send();
        }
        return JmsConstant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/convertAndSend")
    public Object convertAndSend(){
        ptpProducer.convertAndSend();
        return JmsConstant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/multiDataSend")
    @Transactional
    public Object multiDataSend(){
//        cityMapper.insert("1");
//        User user = new User();
//        user.setName("activemq");
//        user.setUserHight(BigDecimal.valueOf(11.35));
//        userMapper.insert(user);
        ptpProducer.send();
//        cityMapper.findByState("CA");
//        return cityMapper.count();
        return "Produce";
    }
}
