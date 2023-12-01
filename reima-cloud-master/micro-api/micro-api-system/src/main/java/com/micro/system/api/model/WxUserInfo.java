package com.micro.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("WxUserInfo")
public class WxUserInfo implements Serializable {
    private static final long serialVersionUID = -5446101298951995448L;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("国家")
    private String country;
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("语言")
    private String language;
    @ApiModelProperty("性别")
    private Byte gender;

    private String openId;

    private String sessionKey;

    private String inviteMobile;

    public String getInviteMobile() {
        return inviteMobile;
    }

    public void setInviteMobile(String inviteMobile) {
        this.inviteMobile = inviteMobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
