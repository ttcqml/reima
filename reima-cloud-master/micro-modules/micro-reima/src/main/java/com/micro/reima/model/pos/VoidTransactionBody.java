package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("VoidTransactionBody")
public class VoidTransactionBody implements Serializable {
    private static final long serialVersionUID = -6085525425909437865L;
    @ApiModelProperty("原始订单号")
    private String original_invoice_no;

    public String getOriginal_invoice_no() {
        return original_invoice_no;
    }

    public void setOriginal_invoice_no(String original_invoice_no) {
        this.original_invoice_no = original_invoice_no;
    }
}
