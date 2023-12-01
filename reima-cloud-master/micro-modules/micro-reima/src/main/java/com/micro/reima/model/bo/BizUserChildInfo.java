package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("BizUserChildInfo")
public class BizUserChildInfo implements Serializable {

  private static final long serialVersionUID = 5199812938500370797L;

  @ApiModelProperty("主键")
  private Long id;

  /** 姓名 */
  @ApiModelProperty("姓名")
  private String name;

  /** 性别 （0男 1女 2未知） */
  @ApiModelProperty("性别 （0男 1女 2未知）")
  private String sex;

  /** 生日 */
  @ApiModelProperty("生日")
  private String birthday;

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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
}
