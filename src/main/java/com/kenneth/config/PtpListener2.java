package com.kenneth.config;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.kenneth.service.JmsConstant;

/**
 * 点对点模式: 消费者2
 * Created by jason-geng on 5/21/17.
 */
//@Component
public class PtpListener2 {

//    @JmsListener(destination = JmsConstant.QUEUE_NAME, containerFactory = JmsConstant.QUEUE_CONTAINER)
//    public void receive(String msg){
//        System.out.println("点对点模式2: " + msg);
//    }
}
