package com.micro.reima.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("ExCouponVo")
public class ExCouponVo implements Serializable {

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 优惠券ID */
  @ApiModelProperty("优惠券ID")
  private Long couponId;

  /** 优惠券名称 */
  @ApiModelProperty("优惠券名称")
  private String name;

  /** 优惠券介绍,通常是显示优惠券使用限 */
  @ApiModelProperty("优惠券介绍,通常是显示优惠券使用限")
  private String breif;

  /** 优惠券标签,例如新人专用 */
  @ApiModelProperty("优惠券标签,例如新人专用")
  private String tag;

  /** 优惠金额, */
  @ApiModelProperty("优惠金额,")
  private BigDecimal discount;

  /** 最少消费金额才能使用优惠券 */
  @ApiModelProperty("最少消费金额才能使用优惠券")
  private BigDecimal min;

  /** 核销码 */
  @ApiModelProperty("核销码")
  private String verificationCode;

  /** 使用状态, 如果是0则未使用,如果是1则已使用,如果是2则已过期,如果是3则已经下架, */
  @ApiModelProperty("使用状态, 如果是0则未使用,如果是1则已使用,如果是2则已过期,如果是3则已经下架,")
  private Long status;

  /** 有效期开始时间 */
  @ApiModelProperty("有效期开始时间")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startTime;

  /** 有效期截至时间 */
  @ApiModelProperty("有效期截至时间")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date endTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCouponId() {
    return couponId;
  }

  public void setCouponId(Long couponId) {
    this.couponId = couponId;
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

  public String getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
}
