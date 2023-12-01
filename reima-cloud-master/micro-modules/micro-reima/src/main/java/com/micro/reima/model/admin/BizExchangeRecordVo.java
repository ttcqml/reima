package com.micro.reima.model.admin;

import com.micro.common.core.annotation.Excel;
import com.micro.reima.domain.BizExchangeRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("BizExchangeRecordVo")
public class BizExchangeRecordVo extends BizExchangeRecord {

  /** 用户昵称 */
  @ApiModelProperty("用户昵称")
  @Excel(name = "用户昵称")
  private String nickName;
  /** 手机号码 */
  @ApiModelProperty("手机号码")
  @Excel(name = "手机号码")
  private String phonenumber;
  /** 用户头像 */
  @ApiModelProperty("用户头像")
  private String avatar;

  @ApiModelProperty("兑换名称")
  @Excel(name = "兑换名称")
  private String exName;

  public String getExName() {
    return exName;
  }

  public void setExName(String exName) {
    this.exName = exName;
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
