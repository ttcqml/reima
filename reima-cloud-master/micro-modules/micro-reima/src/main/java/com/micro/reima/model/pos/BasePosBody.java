package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("BasePosBody")
public class BasePosBody implements Serializable {
    private static final long serialVersionUID = -1558833737008493956L;
    @ApiModelProperty("通过接口“getToken”获得")
    private String access_token;
    @ApiModelProperty("建议保持其唯一性")
    private String request_id;
    @ApiModelProperty("时间戳，例如“1526366974000”")
    private String timestamp;
    @ApiModelProperty("可以按照以下模式“request_id&timestamp&requestBody&”进行连接后做MD5处理")
    private String signature;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
