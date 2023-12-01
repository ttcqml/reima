package com.micro.reima.model.admin;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("CorrectUserAccBody")
public class CorrectUserAccBody implements Serializable {

    private String key;

    private Long userId;

    private BigDecimal amt;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}
