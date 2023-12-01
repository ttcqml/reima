package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("数云会员对象")
public class ShuYunMemInfoVo implements Serializable {

  @ApiModelProperty("生日 2018-12-25 ")
  private String birthday;
  @ApiModelProperty("性别 F女性 M男性")
  private String gender;
  @ApiModelProperty("会员创建时间 2018-12-25 09:30:00")
  private String created;
  @ApiModelProperty("手机号码")
  private String mobile;
  @ApiModelProperty("导购ID")
  private String guideId;
  @ApiModelProperty("等级过期时间")
  private String gradeExpired;
  @ApiModelProperty("累计活得积分")
  private Integer gainedPoint;
  @ApiModelProperty("可用积分")
  private Integer point;
  @ApiModelProperty("开卡平台")
  private String platCode;
  @ApiModelProperty("累计过期积分")
  private Integer expiredPoint;
  @ApiModelProperty("等级更新时间 yyyy-MM-dd HH:mm:ss ")
  private String gradeModified;
  @ApiModelProperty("等级, 从1开始 ")
  private Integer grade;
  @ApiModelProperty("会员名称")
  private String  name;
  @ApiModelProperty("累计消费积分")
  private Integer consumedPoint;
  @ApiModelProperty("平台账号")
  private String id;
  @ApiModelProperty("开卡店铺ID")
  private String shopId;
  @ApiModelProperty("会员ID ")
  private Integer memberId;

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getGuideId() {
    return guideId;
  }

  public void setGuideId(String guideId) {
    this.guideId = guideId;
  }

  public String getGradeExpired() {
    return gradeExpired;
  }

  public void setGradeExpired(String gradeExpired) {
    this.gradeExpired = gradeExpired;
  }

  public Integer getGainedPoint() {
    return gainedPoint;
  }

  public void setGainedPoint(Integer gainedPoint) {
    this.gainedPoint = gainedPoint;
  }

  public Integer getPoint() {
    return point;
  }

  public void setPoint(Integer point) {
    this.point = point;
  }

  public String getPlatCode() {
    return platCode;
  }

  public void setPlatCode(String platCode) {
    this.platCode = platCode;
  }

  public Integer getExpiredPoint() {
    return expiredPoint;
  }

  public void setExpiredPoint(Integer expiredPoint) {
    this.expiredPoint = expiredPoint;
  }

  public String getGradeModified() {
    return gradeModified;
  }

  public void setGradeModified(String gradeModified) {
    this.gradeModified = gradeModified;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getConsumedPoint() {
    return consumedPoint;
  }

  public void setConsumedPoint(Integer consumedPoint) {
    this.consumedPoint = consumedPoint;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getShopId() {
    return shopId;
  }

  public void setShopId(String shopId) {
    this.shopId = shopId;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }
}
