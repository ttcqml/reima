package com.micro.reima.model.vo;

import com.micro.common.core.annotation.Excel;
import com.micro.reima.domain.BizUserAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("BizUserAddressVo")
public class BizUserAddressVo extends BizUserAddress {

  @ApiModelProperty("省份名称")
  @Excel(name = "省份名称")
  private String proName;
  @ApiModelProperty("城市名称")
  @Excel(name = "城市名称")
  private String cityName;
  @ApiModelProperty("区域名称")
  @Excel(name = "区域名称")
  private String areaName;

  /** 用户昵称 */
  @ApiModelProperty("用户昵称")
  private String nickName;
  /** 手机号码 */
  @ApiModelProperty("手机号码")
  private String phonenumber;
  /** 用户头像 */
  @ApiModelProperty("用户头像")
  private String avatar;

  public String getProName() {
    return proName;
  }

  public void setProName(String proName) {
    this.proName = proName;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
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
}
