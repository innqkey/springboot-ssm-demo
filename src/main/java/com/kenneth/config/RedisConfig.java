package com.kenneth.config;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;

import redis.clients.jedis.JedisPoolConfig;


/**
 * redis的配置类
 * @author Administrator
 *
 */
@Configuration
@PropertySource("classpath:redisConfig.properties")
@EnableAutoConfiguration  
public class RedisConfig {  
  
    private static Logger logger = Logger.getLogger(RedisConfig.class);  
    
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true); 
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }
    
    @Bean  
    @ConfigurationProperties(prefix="redis")  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
      
    @SuppressWarnings("deprecation")
    @Bean  
    @ConfigurationProperties(prefix="redis")  
    public JedisConnectionFactory getConnectionFactory(){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig();  
        factory.setPoolConfig(config); 
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }  
      
    
    /**
     * 设置数据存入 redis 的序列化方式
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean("redisTemplate")  
    public StringRedisTemplate getRedisTemplate(){  
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(getConnectionFactory());
        //redis   开启事务
        template.setEnableTransactionSupport(true);
//        RedisSerializer stringSerializer = new StringRedisSerializer();
//        template.setStringSerializer(stringSerializer);
//        template.setKeySerializer(stringSerializer);
//        template.setValueSerializer(fastJson2JsonRedisSerializer());
//        template.setHashKeySerializer(stringSerializer);//key的序列化适配器  
        template.setHashValueSerializer(fastJson2JsonRedisSerializer());//value的序列化适配器，也可以自己编写，大部分场景StringRedisSerializer足以满足需求了。
        template.afterPropertiesSet();
        return template;  
    }  
    
}
