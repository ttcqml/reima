package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ShuYunPointChangeMsgForm implements Serializable {

    @ApiModelProperty("会员号")
    private String memberNo;
    @ApiModelProperty("变更数量")
    private Integer num;
    @ApiModelProperty("变更原因")
    private String reason;
    @ApiModelProperty("当前余额")
    private Integer balance;
    @ApiModelProperty("备注信息")
    private String remark;

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
