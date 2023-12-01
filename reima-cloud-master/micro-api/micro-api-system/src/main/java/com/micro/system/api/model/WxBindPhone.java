package com.micro.system.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("WxBindPhoneBody")
public class WxBindPhone implements Serializable {
    private static final long serialVersionUID = 1316869239381247815L;

    @ApiModelProperty("encryptedData")
    private String encryptedData;
    @ApiModelProperty("iv")
    private String iv;
    @ApiModelProperty("code")
    private String code;

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
