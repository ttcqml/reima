package com.micro.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.micro.common.security.annotation.EnableCustomConfig;
import com.micro.common.security.annotation.EnableRyFeignClients;
import com.micro.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class SystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SystemApplication.class, args);
        System.out.println("系统模块启动成功.");
    }
}
