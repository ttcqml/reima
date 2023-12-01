package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

@ApiModel("BizUserInfoBo")
public class BizUserInfoBo implements Serializable {

  private static final long serialVersionUID = 5090632307734734211L;

  @ApiModelProperty("主键")
  private Long id;

  @ApiModelProperty("姓名")
  private String username;

  @ApiModelProperty("手机号")
  private String phonenumber;

  @ApiModelProperty("生日")
  private String birthday;

  @ApiModelProperty("积分数量")
  private Integer points;

  /** 性别 （0男 1女 2未知） */
  @ApiModelProperty("性别 （0男 1女 2未知）")
  private String sex;

  /** 孩子数量 */
  @ApiModelProperty("孩子数量")
  private Long childNum;

  /** 省份 */
  @ApiModelProperty("省份")
  private Long provinceId;

  /** 城市 */
  @ApiModelProperty("城市")
  private Long cityId;

  @ApiModelProperty("注册日期")
  private String createTime;

  private List<BizUserChildInfo> bizUserChildInfoBoList;

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Long getChildNum() {
    return childNum;
  }

  public void setChildNum(Long childNum) {
    this.childNum = childNum;
  }

  public Long getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public List<BizUserChildInfo> getBizUserChildInfoBoList() {
    return bizUserChildInfoBoList;
  }

  public void setBizUserChildInfoBoList(
      List<BizUserChildInfo> bizUserChildInfoBoList) {
    this.bizUserChildInfoBoList = bizUserChildInfoBoList;
  }
}
