package com.micro.reima.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("SimpleCouponVo")
public class SimpleCouponVo implements Serializable {

  private static final long serialVersionUID = 5037447667178050126L;

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 优惠券名称 */
  @ApiModelProperty("优惠券名称")
  private String name;

  @ApiModelProperty("0现金券 1折扣券")
  private Long type;

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

  /** 有效时间限制,如果是0,则基于领取时间的有效天数days,如果是1,则start_time和end_time是优惠券有效期 */
  @ApiModelProperty("有效时间限制,如果是0,则基于领取时间的有效天数days,如果是1,则start_time和end_time是优惠券有效期")
  private Long timeType;

  /** 基于领取时间的有效天数days */
  @ApiModelProperty("基于领取时间的有效天数days")
  private Long days;

  /** 兑换所需积分数量 */
  @ApiModelProperty("兑换所需积分数量")
  private BigDecimal exPoints;

  /** 使用券开始时间 */
  @ApiModelProperty("使用券开始时间")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date startTime;

  /** 使用券截至时间 */
  @ApiModelProperty("使用券截至时间")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date endTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
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

  public Long getTimeType() {
    return timeType;
  }

  public void setTimeType(Long timeType) {
    this.timeType = timeType;
  }

  public Long getDays() {
    return days;
  }

  public void setDays(Long days) {
    this.days = days;
  }

  public BigDecimal getExPoints() {
    return exPoints;
  }

  public void setExPoints(BigDecimal exPoints) {
    this.exPoints = exPoints;
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
