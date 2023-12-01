package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("SimpleUserInfo")
public class SimpleUserInfo implements Serializable {

    private Long userId;

    private String phonenumber;

    private String memberId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
