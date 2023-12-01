package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("TransactionDetail")
public class TransactionDetail implements Serializable {

    private static final long serialVersionUID = -5969975338207097606L;
    @ApiModelProperty("商品编码")
    private String product_code;
    @ApiModelProperty("商品规格编码")
    private String product_spec_code;
    @ApiModelProperty("实际金额")
    private String real_amount;
    @ApiModelProperty("数量")
    private String quantity;
    @ApiModelProperty("折扣率")
    private String discount;
    @ApiModelProperty("单价")
    private String unit_price;
    @ApiModelProperty("实际单价")
    private String real_price;
    @ApiModelProperty("员工编码")
    private String employee_code;

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_spec_code() {
        return product_spec_code;
    }

    public void setProduct_spec_code(String product_spec_code) {
        this.product_spec_code = product_spec_code;
    }

    public String getReal_amount() {
        return real_amount;
    }

    public void setReal_amount(String real_amount) {
        this.real_amount = real_amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getReal_price() {
        return real_price;
    }

    public void setReal_price(String real_price) {
        this.real_price = real_price;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }
}
