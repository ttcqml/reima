package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("SimpleUserAddressVo")
public class SimpleUserAddressVo implements Serializable {

  /** 收货人名称 */
  @ApiModelProperty("收货人名称")
  private String name;

  /** 收货人手机号 */
  @ApiModelProperty("收货人手机号")
  private String tel;

  /** 省份主键 */
  @ApiModelProperty("省份主键")
  private Long province;

  /** 市区主键 */
  @ApiModelProperty("市区主键")
  private Long city;

  /** 区县主键 */
  @ApiModelProperty("区县主键")
  private Long area;

  /** 详细收货地址 */
  @ApiModelProperty("详细收货地址")
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public Long getProvince() {
    return province;
  }

  public void setProvince(Long province) {
    this.province = province;
  }

  public Long getCity() {
    return city;
  }

  public void setCity(Long city) {
    this.city = city;
  }

  public Long getArea() {
    return area;
  }

  public void setArea(Long area) {
    this.area = area;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
