package com.kenneth.config;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;

import com.kenneth.service.JmsConstant;

/**
 * @author qinkai
 * @date 2018年8月8日
 */
@Configuration
@PropertySource("classpath:activemqConfig.properties")
@EnableAutoConfiguration
@EnableJms
public class ActiveMQConfig {
    @Value("${jms.broker-url}")
    private String jmsBrokerUrl;

    @Value("${jms.user}")
    private String jmsUser;

    @Value("${jms.password}")
    private String jmsPassword;
    
    /**
     * 设置发送策略
     * @return
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
            RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
            //是否在每次尝试重新发送失败后,增长这个等待时间
            redeliveryPolicy.setUseExponentialBackOff(true);
            //重发次数,默认为6次   这里设置为10次
            redeliveryPolicy.setMaximumRedeliveries(10);
            //重发时间间隔,默认为1秒
            redeliveryPolicy.setInitialRedeliveryDelay(1);
            //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
            redeliveryPolicy.setBackOffMultiplier(2);
            //是否避免消息碰撞
            redeliveryPolicy.setUseCollisionAvoidance(false);
            //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
            redeliveryPolicy.setMaximumRedeliveryDelay(-1);
            return redeliveryPolicy;
    }

    /**
     * 创建 ActiveMQ 的连接工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        // BaseConstant
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(jmsBrokerUrl);
        connectionFactory.setUserName(jmsUser);
        connectionFactory.setPassword(jmsPassword);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        return connectionFactory;
    }

    /**
     * JMS 队列的模板类
     */
    @Bean
    public JmsTemplate jmsQueueTemplate() {
        JmsTemplate template = new JmsTemplate(connectionFactory());
        //deliveryMode, priority, timeToLive 的开关，要生效，必须配置为true，默认false
        template.setExplicitQosEnabled(true);
        //消息的应答方式，需要手动确认，此时SessionTransacted必须被设置为false，且为Session.CLIENT_ACKNOWLEDGE模式
//        template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return template;
    }

    /**
     * JMS 队列的监听容器工厂
     */
    @Bean(name = JmsConstant.QUEUE_CONTAINER)
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        // 设置连接数
        factory.setConcurrency("3-10");
        // 重连间隔时间
        factory.setRecoveryInterval(1000L);
//        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    /**
     * JMS 发布订阅的监听容器工厂
     */
    @Bean(name = JmsConstant.TOPIC_CONTAINER)
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1");
        factory.setPubSubDomain(true);
        return factory;
    }

    /**
     * JMS 发布订阅的模板类
     */
    @Bean
    public JmsTemplate jmsTopicTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    /**
     * 本地事务
     */
//    @Bean
//    public JmsTransactionManager jmsTransactionManager() {
//        return new JmsTransactionManager(connectionFactory());
//    }
}
