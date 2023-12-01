package com.micro.reima.domain;

import java.math.BigDecimal;
import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单明细对象 biz_order_detail
 * 
 * @author micro
 * @date 2022-01-12
 */
@ApiModel("BizOrderDetail")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 订单主键 */
    @ApiModelProperty("订单主键")
    @Excel(name = "订单主键")
    private Long orderId;

    /** 产品图片 */
    @ApiModelProperty("产品图片")
    @Excel(name = "产品图片")
    private String url;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    @Excel(name = "产品名称")
    private String prdName;

    /** 商品编码 */
    @ApiModelProperty("商品编码")
    @Excel(name = "商品编码")
    private String prdCode;

    /** 商品规格编码 */
    @ApiModelProperty("商品规格编码")
    @Excel(name = "商品规格编码")
    private String prdSpec;

    /** 数量 */
    @ApiModelProperty("数量")
    @Excel(name = "数量")
    private Long num;

    /** 折扣率 */
    @ApiModelProperty("折扣率")
    @Excel(name = "折扣率")
    private BigDecimal discount;

    /** 单价 */
    @ApiModelProperty("单价")
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 实际价格 */
    @ApiModelProperty("实际价格")
    @Excel(name = "实际价格")
    private BigDecimal realPrice;

    /** 员工编码 */
    @ApiModelProperty("员工编码")
    @Excel(name = "员工编码")
    private String employeeCode;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("url", getUrl())
            .append("prdName", getPrdName())
            .append("prdCode", getPrdCode())
            .append("prdSpec", getPrdSpec())
            .append("num", getNum())
            .append("discount", getDiscount())
            .append("unitPrice", getUnitPrice())
            .append("realPrice", getRealPrice())
            .append("employeeCode", getEmployeeCode())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
