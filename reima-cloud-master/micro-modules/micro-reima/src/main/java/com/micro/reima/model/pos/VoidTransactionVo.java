package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("VoidTransactionVo")
@JsonInclude(value = Include.ALWAYS)
public class VoidTransactionVo implements Serializable {
    private static final long serialVersionUID = 1496103222417962301L;
    @ApiModelProperty("原始订单号")
    private String original_invoice_no;

    public VoidTransactionVo() {
    }

    public VoidTransactionVo(String original_invoice_no) {
        this.original_invoice_no = original_invoice_no;
    }

    public String getOriginal_invoice_no() {
        return original_invoice_no;
    }

    public void setOriginal_invoice_no(String original_invoice_no) {
        this.original_invoice_no = original_invoice_no;
    }
}
