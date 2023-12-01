package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("同步退单类")
public class RefundOrderBo {
    private Long id ;
    @ApiModelProperty("订单ID voidTransaction接口original_invoice_no")
    private String orderId;
    @ApiModelProperty("店铺Id 根据order_id关联原订单shop_id")
    private String shopId;
    @ApiModelProperty("子订单ID 主订单OrderId + 商品ID")
    private String orderItemId;
    @ApiModelProperty("商品Id 根据order_id关联原订单product_id")
    private String productId;
    @ApiModelProperty("退款金额 根据order_id关联原订单payment")
    private String refundFee;
    @ApiModelProperty("根据order_id关联原订单子订单sku_id")
    private String skuId;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
