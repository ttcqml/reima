package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel("MemberCouponVo")
@JsonInclude(value = Include.ALWAYS)
public class MemberCouponVo implements Serializable {
    private static final long serialVersionUID = 4849431907159528180L;
    @ApiModelProperty("会员编码")
    private String member_code;
    @ApiModelProperty("优惠券名称")
    private String coupon_name;
    @ApiModelProperty("优惠券类型编码")
    private String coupon_type_code;
    @ApiModelProperty("优惠券金额")
    private BigDecimal coupon_value;
    @ApiModelProperty("优惠券序列号")
    private String coupon_serial_no;
    @ApiModelProperty("生效日期 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GMT+8")
    private Date effective_date;
    @ApiModelProperty("失效日期 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GMT+8")
    private Date expired_date;
    @ApiModelProperty("状态：0:inactive,1:normal,2:use,3:expiry")
    private Integer status;
    @ApiModelProperty("创建日期 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GMT+8")
    private Date create_date;
    @ApiModelProperty("使用日期 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "GMT+8")
    private Date issue_date;
    @ApiModelProperty("优惠券编码")
    private String coupon_code;
    @ApiModelProperty("优惠券类型描述")
    private String coupon_type_name="Cash coupon";
    @ApiModelProperty("优惠券辅助信息")
    private CouponInfoVo coupon_info;// 待确认

    @JsonIgnore
    private BigDecimal min;

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_type_code() {
        return coupon_type_code;
    }

    public void setCoupon_type_code(String coupon_type_code) {
        this.coupon_type_code = coupon_type_code;
    }

    public BigDecimal getCoupon_value() {
        return coupon_value;
    }

    public void setCoupon_value(BigDecimal coupon_value) {
        this.coupon_value = coupon_value;
    }

    public String getCoupon_serial_no() {
        return coupon_serial_no;
    }

    public void setCoupon_serial_no(String coupon_serial_no) {
        this.coupon_serial_no = coupon_serial_no;
    }

    public Date getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(Date effective_date) {
        this.effective_date = effective_date;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getCoupon_type_name() {
        return coupon_type_name;
    }

    public void setCoupon_type_name(String coupon_type_name) {
        this.coupon_type_name = coupon_type_name;
    }

    public CouponInfoVo getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(CouponInfoVo coupon_info) {
        this.coupon_info = coupon_info;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }
}
