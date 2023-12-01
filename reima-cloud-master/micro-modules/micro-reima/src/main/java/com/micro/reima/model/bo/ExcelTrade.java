package com.micro.reima.model.bo;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ExcelTrade implements Serializable {

    @Excel(name = "手机号")
    private String member_code;
    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    @Excel(name = "日期")
    private Date purchase_date;
    @ApiModelProperty("交易时间 yyyy-MM-dd HH:mm:ss")
    @Excel(name = "日期")
    private Date transaction_date;
    @ApiModelProperty("交易类型编码")
    @Excel(name = "")
    private String transaction_type_code;
    @ApiModelProperty("订单编号")
    @Excel(name = "单据编号")
    private String invoice_no;
    @ApiModelProperty("原始订单号")
    @Excel(name = "")
    private String original_invoice_no;
    @ApiModelProperty("店铺编号")
    @Excel(name = "")
    private String store_code;
    @ApiModelProperty("渠道编码")
    @Excel(name = "")
    private String channel_code;
    @ApiModelProperty("员工编码")
    @Excel(name = "")
    private String employee_code;
    @ApiModelProperty("订单总额")
    @Excel(name = "消费金额")
    private String total_amount;
    @ApiModelProperty("订单总数")
    @Excel(name = "消费数量")
    private String total_quantity;
    @ApiModelProperty("折扣率")
    @Excel(name = "单据折扣")
    private String total_discount;

    // 订单明细
    @ApiModelProperty("商品编码")
    @Excel(name = "商品代码")
    private String product_code;
    @ApiModelProperty("商品规格编码")
    @Excel(name = "")
    private String product_spec_code;
    @ApiModelProperty("实际金额")
    @Excel(name = "商品价格")
    private String real_amount;
    @ApiModelProperty("数量")
    @Excel(name = "消费数量")
    private String quantity;
    @ApiModelProperty("折扣率")
    @Excel(name = "")
    private String discount;
    @ApiModelProperty("单价")
    @Excel(name = "")
    private String unit_price;
    @ApiModelProperty("实际单价")
    @Excel(name = "")
    private String real_price;
    // 支付方式
    @ApiModelProperty("现金、银行卡、电子优惠券、支付宝、微信、积分抵扣")
    @Excel(name = "")
    private String tender_type_code;
    @ApiModelProperty("实际金额")
    @Excel(name = "")
    private String realy_amount;
    @ApiModelProperty("源号码")
    @Excel(name = "")
    private String source_no;
    @ApiModelProperty("折扣率")
    @Excel(name = "")
    private String discount_point;
    @ApiModelProperty("优惠券序列号")
    @Excel(name = "")
    private String coupon_serial_no;

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type_code() {
        return transaction_type_code;
    }

    public void setTransaction_type_code(String transaction_type_code) {
        this.transaction_type_code = transaction_type_code;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getOriginal_invoice_no() {
        return original_invoice_no;
    }

    public void setOriginal_invoice_no(String original_invoice_no) {
        this.original_invoice_no = original_invoice_no;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(String total_discount) {
        this.total_discount = total_discount;
    }

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

    public String getTender_type_code() {
        return tender_type_code;
    }

    public void setTender_type_code(String tender_type_code) {
        this.tender_type_code = tender_type_code;
    }

    public String getRealy_amount() {
        return realy_amount;
    }

    public void setRealy_amount(String realy_amount) {
        this.realy_amount = realy_amount;
    }

    public String getSource_no() {
        return source_no;
    }

    public void setSource_no(String source_no) {
        this.source_no = source_no;
    }

    public String getDiscount_point() {
        return discount_point;
    }

    public void setDiscount_point(String discount_point) {
        this.discount_point = discount_point;
    }

    public String getCoupon_serial_no() {
        return coupon_serial_no;
    }

    public void setCoupon_serial_no(String coupon_serial_no) {
        this.coupon_serial_no = coupon_serial_no;
    }
}
