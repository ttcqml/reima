package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("MemberInfoBody")
public class MemberInfoBody implements Serializable {

    private static final long serialVersionUID = 2334165457215964612L;
    @ApiModelProperty("手机号码")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
