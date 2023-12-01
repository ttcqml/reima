package com.micro.reima.model.bo;

import java.io.Serializable;

public class SkuDetail implements Serializable {

  private String color_code;

  private String color_name;

  public String getColor_code() {
    return color_code;
  }

  public void setColor_code(String color_code) {
    this.color_code = color_code;
  }

  public String getColor_name() {
    return color_name;
  }

  public void setColor_name(String color_name) {
    this.color_name = color_name;
  }
}
