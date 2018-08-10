package com.kenneth.config;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.kenneth.service.JmsConstant;

/**
 * 点对点模式: 消费者2
 * Created by jason-geng on 5/21/17.
 */
@Component
public class PtpListener1 {

    private final static Logger logger = LoggerFactory.getLogger(PtpListener1.class);
    
    @JmsListener(destination = JmsConstant.QUEUE_NAME, containerFactory = JmsConstant.QUEUE_CONTAINER)
    public void receive(final TextMessage text, Session session) throws JMSException{
        try {
            logger.info("ptp 接收到的消息为：" + text.getText());
//            text.acknowledge(); //使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            logger.info("ptp 消息重发**************************");
            session.recover();
        }
    }
}
