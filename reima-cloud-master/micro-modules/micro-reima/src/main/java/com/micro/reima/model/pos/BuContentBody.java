package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("Body")
public class BuContentBody<T> implements Serializable
{
  @ApiModelProperty("返回数据")
  private T bu_content;

  public BuContentBody() {
  }

  public BuContentBody(T bu_content) {
    this.bu_content = bu_content;
  }

  public T getBu_content() {
    return bu_content;
  }

  public void setBu_content(T bu_content) {
    this.bu_content = bu_content;
  }
}
