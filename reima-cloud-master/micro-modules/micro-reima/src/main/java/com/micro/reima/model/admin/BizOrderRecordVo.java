package com.micro.reima.model.admin;

import com.micro.common.core.annotation.Excel;
import com.micro.reima.domain.BizOrderRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("BizOrderRecordVo")
public class BizOrderRecordVo extends BizOrderRecord implements Serializable {

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
