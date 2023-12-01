package com.micro.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("WxLoginInfo")
public class WxLoginInfo implements Serializable {

    private static final long serialVersionUID = 9194310540002343642L;

    @ApiModelProperty("微信授权码")
    private String code;

    @ApiModelProperty("邀请人手机号")
    private String invitation;

    @ApiModelProperty("微信用户信息")
    private WxUserInfo userInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public WxUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
