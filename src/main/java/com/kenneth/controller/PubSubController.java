package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenneth.config.PubSubProducer;
import com.kenneth.service.JmsConstant;

/**
 * 发布/订阅 - 调用生产者
 * Created by jason-geng on 5/21/17.
 */
@RestController
@RequestMapping(value = "/pubSub")
public class PubSubController {

    @Autowired
    private PubSubProducer pubSubProducer;

    @RequestMapping(value = "/send")
    public String send(){
        pubSubProducer.send();
        return JmsConstant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/convertAndSend")
    public String convertAndSend(){
        pubSubProducer.convertAndSend();
        return JmsConstant.RESPONSE_SUCCESS;
    }
}
