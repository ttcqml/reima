package com.micro.reima.model.bo;


public class UpTokenChildForm {
    private String isOverDue;
    private String authSource;
    private String authValue;
    private String appId;
    private String authType;
    private String accessToken;

    public String getIsOverDue() {
        return isOverDue;
    }

    public void setIsOverDue(String isOverDue) {
        this.isOverDue = isOverDue;
    }

    public String getAuthSource() {
        return authSource;
    }

    public void setAuthSource(String authSource) {
        this.authSource = authSource;
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
