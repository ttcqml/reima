package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("UserInfoVo")
public class UserInfoVo implements Serializable {

  private static final long serialVersionUID = -3152269448559196226L;

  @ApiModelProperty("用户主键")
  private Long userId;
  @ApiModelProperty("昵称")
  private String nickName;
  @ApiModelProperty("手机号")
  private String phonenumber;
  @ApiModelProperty("会员等级")
  private String vipDesc;
  @ApiModelProperty("会员码")
  private String vipCode;
  @ApiModelProperty("会员条形码")
  private String vipCodeUrl;
  @ApiModelProperty("头像")
  private String avatar;
  @ApiModelProperty("积分余额")
  private BigDecimal balance;
  @ApiModelProperty("是否有新券 1是 0否")
  private Integer isNew;

  public Integer getIsNew() {
    return isNew;
  }

  public void setIsNew(Integer isNew) {
    this.isNew = isNew;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getVipDesc() {
    return vipDesc;
  }

  public void setVipDesc(String vipDesc) {
    this.vipDesc = vipDesc;
  }

  public String getVipCode() {
    return vipCode;
  }

  public void setVipCode(String vipCode) {
    this.vipCode = vipCode;
  }

  public String getVipCodeUrl() {
    return vipCodeUrl;
  }

  public void setVipCodeUrl(String vipCodeUrl) {
    this.vipCodeUrl = vipCodeUrl;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }
}
