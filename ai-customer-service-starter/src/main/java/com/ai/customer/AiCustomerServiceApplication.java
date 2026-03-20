package com.ai.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AI电商客服系统启动类
 */
@SpringBootApplication(scanBasePackages = "com.ai.customer")
@MapperScan("com.ai.customer.infrastructure.mapper")
public class AiCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCustomerServiceApplication.class, args);
    }
}
