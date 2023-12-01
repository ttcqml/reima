package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("SimpleProductVo")
public class SimpleProductVo implements Serializable {

  private static final long serialVersionUID = 2006936809903902173L;

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 商品名称 */
  @ApiModelProperty("商品名称")
  private String name;

  @ApiModelProperty("配送方式 1快递 2自提")
  private String way;

  /** 商品主图 */
  @ApiModelProperty("商品主图")
  private String url;

  @ApiModelProperty("剩余库存")
  private Integer num;

  /** 商品简介 */
  @ApiModelProperty("商品简介")
  private String brief;

  /** 商品价格 */
  @ApiModelProperty("商品价格")
  private BigDecimal price;

  /** 商品单位 */
  @ApiModelProperty("商品单位")
  private String unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getWay() {
    return way;
  }

  public void setWay(String way) {
    this.way = way;
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

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
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

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }
}
