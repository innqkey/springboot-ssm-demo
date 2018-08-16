package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.common.AppCtxUtil;
import com.kenneth.constant.ContextConstant;
import com.kenneth.service.JmsConstant;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.kenneth.mapper")
//@EnableCaching 
public class AppConfig extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext context = (ApplicationContext)SpringApplication.run(AppConfig.class, args);
        AppCtxUtil.setApplicationContext(context);
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppConfig.class);
	}

	@Bean
	public RestTemplate restTemplate(){
	    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	    requestFactory.setConnectTimeout(1000);
	    requestFactory.setReadTimeout(1000);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	}
}
