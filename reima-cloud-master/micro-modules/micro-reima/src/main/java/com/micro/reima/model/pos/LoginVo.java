package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("LoginVo")
public class LoginVo implements Serializable {

  @ApiModelProperty("Token值")
  private String access_token;
  @ApiModelProperty("Token类型")
  private String token_type;
  @ApiModelProperty("令牌过期时间")
  private Integer expires_in;
  @ApiModelProperty("刷新token")
  private String refresh_token;

  public LoginVo() {
  }

  public LoginVo(String token_type) {
    this.token_type = token_type;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public String getToken_type() {
    return token_type;
  }

  public void setToken_type(String token_type) {
    this.token_type = token_type;
  }

  public Integer getExpires_in() {
    return expires_in;
  }

  public void setExpires_in(Integer expires_in) {
    this.expires_in = expires_in;
  }

  public String getRefresh_token() {
    return refresh_token;
  }

  public void setRefresh_token(String refresh_token) {
    this.refresh_token = refresh_token;
  }
}
