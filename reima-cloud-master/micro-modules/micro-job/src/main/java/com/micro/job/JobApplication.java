package com.micro.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.micro.common.security.annotation.EnableCustomConfig;
import com.micro.common.security.annotation.EnableRyFeignClients;
import com.micro.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class JobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JobApplication.class, args);
        System.out.println("定时任务模块启动成功.");
    }
}
