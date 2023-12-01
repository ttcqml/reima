package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("CheckMemberCouponVo")
@JsonInclude(value = Include.ALWAYS)
public class CheckMemberCouponVo implements Serializable {

    private static final long serialVersionUID = 2293345564171799011L;
    @ApiModelProperty("如果该会员有此优惠券，将返回true,否则为false")
    private String exist;
    @ApiModelProperty("状态：0:inactive,1:normal,2:use,3:expiry")
    private Integer status;

    public CheckMemberCouponVo() {
    }

    public CheckMemberCouponVo(String exist) {
        this.exist = exist;
    }

    public String getExist() {
        return exist;
    }

    public void setExist(String exist) {
        this.exist = exist;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
