package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("同步交易订单类")
public class TradeOrderBo {

    private Long id;
    @ApiModelProperty("订单Id createTransaction接口invoice_no")
    private String orderId;
    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("平台账号 createTransaction接口member_code")
    private String platAccount;
    @ApiModelProperty("订单商品总数 createTransaction接口total_quantity")
    private Integer productNum;
    @ApiModelProperty("手工调整优惠金额 createTransaction接口默认为0，如果total_discount传值则等于total_amount*total_discount")
    private String adjustFee;
    @ApiModelProperty("实付金额 createTransaction接口默认取值total_amount，如果total_discount传值则等于total_amount-手工调整优惠金额")
    private String payment;
    @ApiModelProperty("下单时间 createTransaction接口purchase_date")
    private String created;
    @ApiModelProperty("支付时间 createTransaction接口transaction_date")
    private String payTime;
    @ApiModelProperty("收货人手机号 createTransaction接口member_code")
    private String receiverMobile;
    @ApiModelProperty("交易订单详情")
    private List<TradeOrderChildBo> tradeOrderChildBos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPlatAccount() {
        return platAccount;
    }

    public void setPlatAccount(String platAccount) {
        this.platAccount = platAccount;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getAdjustFee() {
        return adjustFee;
    }

    public void setAdjustFee(String adjustFee) {
        this.adjustFee = adjustFee;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public List<TradeOrderChildBo> getTradeOrderChildBos() {
        return tradeOrderChildBos;
    }

    public void setTradeOrderChildBos(List<TradeOrderChildBo> tradeOrderChildBos) {
        this.tradeOrderChildBos = tradeOrderChildBos;
    }
}
