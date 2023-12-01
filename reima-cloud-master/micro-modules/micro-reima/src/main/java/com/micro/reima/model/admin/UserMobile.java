package com.micro.reima.model.admin;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel("UserMobile")
public class UserMobile implements Serializable {

  private Long couponId;

  private String mobile;

  public Long getCouponId() {
    return couponId;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

}
