package com.jenson.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = "com.jenson.service",
        exclude = SecurityAutoConfiguration.class
)
@EnableJpaRepositories(basePackages = {"com.jenson.service"})
@EntityScan("com.jenson.service")
@MapperScan("com.jenson.service.mapper")
public class TemplateServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(TemplateServiceMain.class,args);
    }
}
