package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("BalanceVo")
public class BalanceVo implements Serializable {

  @ApiModelProperty("余额")
  private BigDecimal balance;

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BalanceVo() {
  }

  public BalanceVo(BigDecimal balance) {
    this.balance = balance;
  }
}
