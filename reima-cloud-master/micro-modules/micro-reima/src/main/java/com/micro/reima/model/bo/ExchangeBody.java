package com.micro.reima.model.bo;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("ExchangeBody")
public class ExchangeBody implements Serializable {

  /** 兑换主键 如果是商品则关联商品主键,如果是优惠券则关联我的优惠券主键 */
  @ApiModelProperty("兑换主键 如果是商品则关联商品主键,如果是优惠券则关联优惠券主键")
  private Long exid;

  /** 兑换类型（0兑换商品 1兑换优惠券） */
  @ApiModelProperty("兑换类型（0兑换商品 1兑换优惠券）")
  private String extype;

  /** 积分数量 */
  @ApiModelProperty("积分数量")
  private BigDecimal amt;

  /** 收货人名称 */
  @ApiModelProperty("收货人名称")
  private String name;

  /** 收货人手机号 */
  @ApiModelProperty("收货人手机号")
  private String tel;

  /** 省份主键 */
  @ApiModelProperty("收货省份主键")
  private Long province;

  /** 市区主键 */
  @ApiModelProperty("收货市区主键")
  private Long city;

  /** 区县主键 */
  @ApiModelProperty("收货区县主键")
  private Long area;

  /** 详细收货地址 */
  @ApiModelProperty("详细收货地址")
  private String address;

  /** 门店名称 */
  @ApiModelProperty("门店名称")
  @Excel(name = "门店名称")
  private String storeName;

  /** 门店地址 */
  @ApiModelProperty("门店地址")
  @Excel(name = "门店地址")
  private String storeAddr;

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getStoreAddr() {
    return storeAddr;
  }

  public void setStoreAddr(String storeAddr) {
    this.storeAddr = storeAddr;
  }

  public Long getExid() {
    return exid;
  }

  public void setExid(Long exid) {
    this.exid = exid;
  }

  public String getExtype() {
    return extype;
  }

  public void setExtype(String extype) {
    this.extype = extype;
  }

  public BigDecimal getAmt() {
    return amt;
  }

  public void setAmt(BigDecimal amt) {
    this.amt = amt;
  }

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
