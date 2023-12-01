package com.micro.reima.model.admin;

import java.io.Serializable;

public class UserInfo implements Serializable {

  private Long userId;

  private String phonenumber;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }
}
