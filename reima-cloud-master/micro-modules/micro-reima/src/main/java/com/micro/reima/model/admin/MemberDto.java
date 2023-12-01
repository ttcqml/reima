package com.micro.reima.model.admin;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel
public class MemberDto implements Serializable {

    private String openId;

    private String phonenumber;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
