package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("CreateTransactionBody")
public class CreateTransactionBody implements Serializable {

    private static final long serialVersionUID = -4581625432029324699L;
    private String member_code;
    @ApiModelProperty("下单时间 yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date purchase_date;
    @ApiModelProperty("交易时间 yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date transaction_date;
    @ApiModelProperty("交易类型编码")
    private String transaction_type_code;
    @ApiModelProperty("订单编号")
    private String invoice_no;
    @ApiModelProperty("原始订单号")
    private String original_invoice_no;
    @ApiModelProperty("店铺编号")
    private String store_code;
    @ApiModelProperty("渠道编码")
    private String channel_code;
    @ApiModelProperty("员工编码")
    private String employee_code;
    @ApiModelProperty("订单总额")
    private String total_amount;
    @ApiModelProperty("订单总数")
    private String total_quantity;
    @ApiModelProperty("折扣率")
    private String total_discount;

    @ApiModelProperty("订单明细")
    private List<TransactionDetail> transaction_details;
    @ApiModelProperty("支付方式")
    private List<TransactionTender> transaction_tenders;

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

    public List<TransactionDetail> getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(
        List<TransactionDetail> transaction_details) {
        this.transaction_details = transaction_details;
    }

    public List<TransactionTender> getTransaction_tenders() {
        return transaction_tenders;
    }

    public void setTransaction_tenders(
        List<TransactionTender> transaction_tenders) {
        this.transaction_tenders = transaction_tenders;
    }
}
