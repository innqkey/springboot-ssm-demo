package com.kenneth.config;

import com.kenneth.service.JmsConstant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 发布/订阅模式: 消费者1
 * Created by jason-geng on 5/21/17.
 */
@Component
public class PubSubListener1 {

    @JmsListener(destination = JmsConstant.TOPIC_NAME, containerFactory = JmsConstant.TOPIC_CONTAINER)
    public void receive(String msg){
        System.out.println("订阅者1 - " + msg);
    }
}
