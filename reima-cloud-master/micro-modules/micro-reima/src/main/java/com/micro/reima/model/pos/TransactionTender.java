package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("TransactionTender")
public class TransactionTender implements Serializable {
    private static final long serialVersionUID = -7922264805918299700L;
    @ApiModelProperty("现金、银行卡、电子优惠券、支付宝、微信、积分抵扣")
    private String tender_type_code;
    @ApiModelProperty("实际金额")
    private String real_amount;
    @ApiModelProperty("源号码")
    private String source_no;
    @ApiModelProperty("折扣率")
    private String discount_point;
    @ApiModelProperty("优惠券序列号")
    private String coupon_serial_no;

    public String getTender_type_code() {
        return tender_type_code;
    }

    public void setTender_type_code(String tender_type_code) {
        this.tender_type_code = tender_type_code;
    }

    public String getReal_amount() {
        return real_amount;
    }

    public void setReal_amount(String real_amount) {
        this.real_amount = real_amount;
    }

    public String getSource_no() {
        return source_no;
    }

    public void setSource_no(String source_no) {
        this.source_no = source_no;
    }

    public String getDiscount_point() {
        return discount_point;
    }

    public void setDiscount_point(String discount_point) {
        this.discount_point = discount_point;
    }

    public String getCoupon_serial_no() {
        return coupon_serial_no;
    }

    public void setCoupon_serial_no(String coupon_serial_no) {
        this.coupon_serial_no = coupon_serial_no;
    }
}
