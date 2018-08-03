package com.kenneth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.kenneth.mapper")
public class AppConfig extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
    
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppConfig.class);
	}

}
