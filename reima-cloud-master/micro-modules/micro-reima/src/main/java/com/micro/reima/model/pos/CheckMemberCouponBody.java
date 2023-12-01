package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("CheckMemberCouponBody")
public class CheckMemberCouponBody implements Serializable {

    private static final long serialVersionUID = 352303990860246151L;
    @ApiModelProperty("会员编码 即会员手机号")
    private String member_code;
    @ApiModelProperty("优惠券序列号")
    private String coupon_serial_no;

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getCoupon_serial_no() {
        return coupon_serial_no;
    }

    public void setCoupon_serial_no(String coupon_serial_no) {
        this.coupon_serial_no = coupon_serial_no;
    }
}
