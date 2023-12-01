package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("同步交易订单子类")
public class TradeOrderChildBo {
    @ApiModelProperty("子订单ID")
    private String orderItemId;
    @ApiModelProperty("商品ID createTransaction接口transaction_detial.product_code")
    private String productId;
    @ApiModelProperty("商品数量 createTransaction接口transaction_detial.quantity")
    private Integer productNum;
    @ApiModelProperty("商品单价 createTransaction接口transaction_detial默认real_price，unit_price不为空时取unit_price")
    private String price;
    @ApiModelProperty("折扣金额 createTransaction接口transaction_detial默认为0，unit_price不为空时等于 (unit_price-real_price)*quantity")
    private String discountFee;
    @ApiModelProperty("商品名称 根据店铺ID、商品id查询本地商品")
    private String productName;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
