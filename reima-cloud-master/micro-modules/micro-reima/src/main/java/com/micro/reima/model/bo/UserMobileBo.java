package com.micro.reima.model.bo;

import java.io.Serializable;

public class UserMobileBo implements Serializable {

  private Long userId;

  private String mobile;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
