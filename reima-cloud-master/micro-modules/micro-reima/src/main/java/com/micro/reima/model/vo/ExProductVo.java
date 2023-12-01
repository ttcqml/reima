package com.micro.reima.model.vo;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("ExProductVo")
public class ExProductVo implements Serializable {

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 商品名称 */
  @ApiModelProperty("商品名称")
  private String name;

  /** 商品主图 */
  @ApiModelProperty("商品主图")
  private String url;

  /** 商品简介 */
  @ApiModelProperty("商品简介")
  private String brief;

  /** 商品价格 */
  @ApiModelProperty("商品价格")
  private BigDecimal price;

  /** 数量 */
  @ApiModelProperty("兑换数量")
  private BigDecimal amt;

  /** 待发货 已发货 */
  @ApiModelProperty("待发货 已发货 待领取 已领取")
  private String state;

  /** 快递公司：顺丰速运 */
  @ApiModelProperty("快递公司：顺丰速运")
  private String express;

  /** 快递单号：SF-001 */
  @ApiModelProperty("快递单号：SF-001")
  private String expressNum;

  /** 领取方式 1快递 2自提 */
  @ApiModelProperty("领取方式 1快递 2自提")
  @Excel(name = "领取方式 1快递 2自提")
  private String way;

  /** 门店名称 */
  @ApiModelProperty("门店名称")
  @Excel(name = "门店名称")
  private String storeName;

  /** 门店地址 */
  @ApiModelProperty("门店地址")
  @Excel(name = "门店地址")
  private String storeAddr;

  public String getWay() {
    return way;
  }

  public void setWay(String way) {
    this.way = way;
  }

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getBrief() {
    return brief;
  }

  public void setBrief(String brief) {
    this.brief = brief;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getAmt() {
    return amt;
  }

  public void setAmt(BigDecimal amt) {
    this.amt = amt;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getExpress() {
    return express;
  }

  public void setExpress(String express) {
    this.express = express;
  }

  public String getExpressNum() {
    return expressNum;
  }

  public void setExpressNum(String expressNum) {
    this.expressNum = expressNum;
  }
}
