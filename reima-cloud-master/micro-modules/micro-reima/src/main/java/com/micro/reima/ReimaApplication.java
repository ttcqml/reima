package com.micro.reima;

import com.micro.common.security.annotation.EnableCustomConfig;
import com.micro.common.security.annotation.EnableRyFeignClients;
import com.micro.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 业务模块
 */
@EnableScheduling
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class ReimaApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ReimaApplication.class, args);
        System.out.println("业务模块启动成功");
    }
}
