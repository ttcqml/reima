package com.micro.reima.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel("UserOrderRecordVo")
public class UserOrderRecordVo implements Serializable {

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 订单金额 */
  @ApiModelProperty("订单金额")
  @Excel(name = "订单金额")
  private BigDecimal amt;

  /** 店铺名称 */
  @ApiModelProperty("店铺名称")
  @Excel(name = "店铺名称")
  private String shopName;

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty("创建时间")
  private Date createTime;

  @ApiModelProperty("商品信息")
  private List<OrderPrdVo> prdVos;

  public List<OrderPrdVo> getPrdVos() {
    return prdVos;
  }

  public void setPrdVos(List<OrderPrdVo> prdVos) {
    this.prdVos = prdVos;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getAmt() {
    return amt;
  }

  public void setAmt(BigDecimal amt) {
    this.amt = amt;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
