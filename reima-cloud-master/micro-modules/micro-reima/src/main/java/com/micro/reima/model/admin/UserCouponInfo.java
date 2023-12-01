package com.micro.reima.model.admin;

import java.io.Serializable;

public class UserCouponInfo implements Serializable {

  private Long id;

  private String startAt;

  private String endAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStartAt() {
    return startAt;
  }

  public void setStartAt(String startAt) {
    this.startAt = startAt;
  }

  public String getEndAt() {
    return endAt;
  }

  public void setEndAt(String endAt) {
    this.endAt = endAt;
  }
}
