package com.micro.reima.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ProConfig {

    @Value("${shuyun.urlHost}")
    private String shuYunUrlHost;

    @Value("${shuyun.upTokenUrl}")
    private String shuYunUpTokenUrl;

    @Value("${pos.appId}")
    private String appId;

    @Value("${pos.appSecret}")
    private String appSecret;

    @Value("${pos.company}")
    private String company;

    @Value("${pos.iposUrl}")
    private String iposUrl;

    @Value("${pos.iposKey}")
    private String iposKey;

    @Value("${pos.iposSecret}")
    private String iposSecret;

    public String getShuYunUrlHost() {
        return shuYunUrlHost;
    }

    public void setShuYunUrlHost(String shuYunUrlHost) {
        this.shuYunUrlHost = shuYunUrlHost;
    }

    public String getShuYunUpTokenUrl() {
        return shuYunUpTokenUrl;
    }

    public void setShuYunUpTokenUrl(String shuYunUpTokenUrl) {
        this.shuYunUpTokenUrl = shuYunUpTokenUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIposUrl() {
        return iposUrl;
    }

    public void setIposUrl(String iposUrl) {
        this.iposUrl = iposUrl;
    }

    public String getIposKey() {
        return iposKey;
    }

    public void setIposKey(String iposKey) {
        this.iposKey = iposKey;
    }

    public String getIposSecret() {
        return iposSecret;
    }

    public void setIposSecret(String iposSecret) {
        this.iposSecret = iposSecret;
    }
}
