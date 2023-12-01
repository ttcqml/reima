package com.micro.reima.model.admin;

import com.micro.common.core.annotation.Excel;
import com.micro.reima.domain.BizUserCoupon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("BizUserCouponVo")
public class BizUserCouponVo extends BizUserCoupon implements Serializable {

  /** 用户昵称 */
  @ApiModelProperty("用户昵称")
  @Excel(name = "用户昵称")
  private String nickName;
  /** 手机号码 */
  @ApiModelProperty("手机号码")
  @Excel(name = "手机号码")
  private String phonenumber;
  /** 用户头像 */
  @ApiModelProperty("用户头像")
  private String avatar;

  /** 优惠券名称 */
  @ApiModelProperty("优惠券名称")
  @Excel(name = "优惠券名称")
  private String name;

  /** 优惠券介绍,通常是显示优惠券使用限 */
  @ApiModelProperty("优惠券介绍,通常是显示优惠券使用限")
  private String breif;

  /** 优惠券标签,例如新人专用 */
  @ApiModelProperty("优惠券标签,例如新人专用")
  private String tag;

  /** 优惠金额, */
  @ApiModelProperty("优惠金额,")
  @Excel(name = "优惠金额")
  private BigDecimal discount;

  /** 最少消费金额才能使用优惠券 */
  @ApiModelProperty("最少消费金额才能使用优惠券")
  @Excel(name = "最少消费金额")
  private BigDecimal min;

  @ApiModelProperty("来源")
  @Excel(name = "来源")
  private String origin;// a 操作账号-登陆账号  b 系统发送-系统  c 用户兑换-用户

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
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

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBreif() {
    return breif;
  }

  public void setBreif(String breif) {
    this.breif = breif;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public BigDecimal getMin() {
    return min;
  }

  public void setMin(BigDecimal min) {
    this.min = min;
  }
}
