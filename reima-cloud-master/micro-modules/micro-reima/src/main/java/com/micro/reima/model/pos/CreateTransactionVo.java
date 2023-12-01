package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("CreateTransactionVo")
@JsonInclude(value = Include.ALWAYS)
public class CreateTransactionVo implements Serializable {
    private static final long serialVersionUID = 1019549831097473665L;
    @ApiModelProperty("订单编号")
    private String invoice_no;

    public CreateTransactionVo() {
    }

    public CreateTransactionVo(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }
}
