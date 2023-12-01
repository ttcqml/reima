package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("GetCouponBody")
public class GetCouponBody implements Serializable {
    private static final long serialVersionUID = -7506986330866106402L;

    @ApiModelProperty("优惠券序列号")
    private String coupon_serial_no;

    public String getCoupon_serial_no() {
        return coupon_serial_no;
    }

    public void setCoupon_serial_no(String coupon_serial_no) {
        this.coupon_serial_no = coupon_serial_no;
    }
}
