package com.micro.reima.model.vo;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OrderPrdVo implements Serializable {

  /** 产品名称 */
  @ApiModelProperty("产品名称")
  @Excel(name = "产品名称")
  private String prdName;

  /** 数量 */
  @ApiModelProperty("数量")
  @Excel(name = "数量")
  private Long num;

  public String getPrdName() {
    return prdName;
  }

  public void setPrdName(String prdName) {
    this.prdName = prdName;
  }

  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }
}
