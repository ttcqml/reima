package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("CouponInfoVo")
@JsonInclude(value = Include.ALWAYS)
public class CouponInfoVo implements Serializable {
    private static final long serialVersionUID = 4343571420487484045L;
    @ApiModelProperty("最低购买条件")
    private BigDecimal minimum_purchase_condition;
    @ApiModelProperty("权益说明")
    private String term_condition = "https://www.reima.com/in";
    @ApiModelProperty("有效期")
    private Integer validity_period = 1;
    @ApiModelProperty("开始时间")
    private Integer relative_start_date = 1;
    @ApiModelProperty("结束时间")
    private Integer relative_end_date = 30;
    @ApiModelProperty("")
    private String specific_date_from;
    @ApiModelProperty("")
    private String specific_date_to;
    @ApiModelProperty("")
    private String stores;

    public CouponInfoVo() {
    }

    public CouponInfoVo(BigDecimal minimum_purchase_condition) {
        this.minimum_purchase_condition = minimum_purchase_condition;
    }

    public BigDecimal getMinimum_purchase_condition() {
        return minimum_purchase_condition;
    }

    public void setMinimum_purchase_condition(BigDecimal minimum_purchase_condition) {
        this.minimum_purchase_condition = minimum_purchase_condition;
    }

    public String getTerm_condition() {
        return term_condition;
    }

    public void setTerm_condition(String term_condition) {
        this.term_condition = term_condition;
    }

    public Integer getValidity_period() {
        return validity_period;
    }

    public void setValidity_period(Integer validity_period) {
        this.validity_period = validity_period;
    }

    public Integer getRelative_start_date() {
        return relative_start_date;
    }

    public void setRelative_start_date(Integer relative_start_date) {
        this.relative_start_date = relative_start_date;
    }

    public Integer getRelative_end_date() {
        return relative_end_date;
    }

    public void setRelative_end_date(Integer relative_end_date) {
        this.relative_end_date = relative_end_date;
    }

    public String getSpecific_date_from() {
        return specific_date_from;
    }

    public void setSpecific_date_from(String specific_date_from) {
        this.specific_date_from = specific_date_from;
    }

    public String getSpecific_date_to() {
        return specific_date_to;
    }

    public void setSpecific_date_to(String specific_date_to) {
        this.specific_date_to = specific_date_to;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }
}
