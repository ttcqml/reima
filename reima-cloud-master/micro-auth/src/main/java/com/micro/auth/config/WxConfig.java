//package com.micro.auth.config;
//
//import cn.binarywang.wx.miniapp.api.WxMaService;
//import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
//import cn.binarywang.wx.miniapp.config.WxMaConfig;
//import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WxConfig {
//    @Autowired
//    private WxProperties properties;
//
//    @Bean
//    public WxMaConfig wxMaConfig() {
//        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
//        config.setAppid(properties.getAppId());
//        config.setSecret(properties.getAppSecret());
//        return config;
//    }
//
//
//    @Bean("wxService")
//    public WxMaService wxService(WxMaConfig maConfig) {
//        WxMaService service = new WxMaServiceImpl();
//        service.setWxMaConfig(maConfig);
//        return service;
//    }
//
//}