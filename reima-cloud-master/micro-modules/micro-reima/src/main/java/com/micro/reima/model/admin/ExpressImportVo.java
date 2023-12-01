package com.micro.reima.model.admin;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("ExpressImportVo")
public class ExpressImportVo implements Serializable {
    private static final long serialVersionUID = 2192064570546481569L;

    /** 快递公司：顺丰速运 */
    @ApiModelProperty("兑换单号")
    @Excel(name = "兑换单号")
    private String orderSn;

    /** 快递公司：顺丰速运 */
    @ApiModelProperty("快递公司：顺丰速运")
    @Excel(name = "快递公司")
    private String express;

    /** 快递单号：SF-001 */
    @ApiModelProperty("快递单号：SF-001")
    @Excel(name = "快递单号")
    private String expressNum;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }
}
