package com.micro.reima.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("SimpleUserSignVo")
public class SimpleUserSignVo implements Serializable {

  private static final long serialVersionUID = 4406669467439675987L;

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 签到日期 */
  @ApiModelProperty("签到日期")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date signAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getSignAt() {
    return signAt;
  }

  public void setSignAt(Date signAt) {
    this.signAt = signAt;
  }
}
