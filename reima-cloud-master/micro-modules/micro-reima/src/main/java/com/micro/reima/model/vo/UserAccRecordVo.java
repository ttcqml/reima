package com.micro.reima.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("UserAccRecordVo")
public class UserAccRecordVo implements Serializable {

  private static final long serialVersionUID = -1338939859939090448L;

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 数量 */
  @ApiModelProperty("数量")
  private BigDecimal amt;

  @ApiModelProperty("状态 0正常 1无效")
  private String status;

  @ApiModelProperty("备注")
  private String memo;

  @JsonFormat(pattern = "yyyy-MM-dd")
  @ApiModelProperty("创建时间")
  private Date createTime;

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
