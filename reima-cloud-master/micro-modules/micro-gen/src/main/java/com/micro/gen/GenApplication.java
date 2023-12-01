package com.micro.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.micro.common.security.annotation.EnableCustomConfig;
import com.micro.common.security.annotation.EnableRyFeignClients;
import com.micro.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class GenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GenApplication.class, args);
        System.out.println("代码生成模块启动成功.");
    }
}
