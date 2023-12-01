package com.micro.reima.model.vo;

import com.micro.reima.domain.BizUserSignin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("BizUserSigninVo")
public class BizUserSigninVo extends BizUserSignin implements Serializable {


  @ApiModelProperty("用户头像")
  private String avatar;
  @ApiModelProperty("用户昵称")
  private String nickName;
  @ApiModelProperty("手机号")
  private String phonenumber;

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
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
}
