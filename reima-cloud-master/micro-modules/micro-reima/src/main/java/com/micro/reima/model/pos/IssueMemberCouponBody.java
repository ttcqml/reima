package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("IssueMemberCouponBody")
public class IssueMemberCouponBody implements Serializable {
    private static final long serialVersionUID = -1014342188072169209L;
    @ApiModelProperty("会员编码 即会员手机号")
    private String member_code;
    @ApiModelProperty("优惠券序列号")
    private String coupon_serial_no;
    @ApiModelProperty("核销时间 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date issue_time;
    @ApiModelProperty("渠道编码：“POS”")
    private String channel_code;

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

    public Date getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(Date issue_time) {
        this.issue_time = issue_time;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }
}
