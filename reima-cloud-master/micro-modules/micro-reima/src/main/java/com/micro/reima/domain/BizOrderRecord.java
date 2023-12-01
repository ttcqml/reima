package com.micro.reima.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 订单对象 biz_order_record
 * 
 * @author micro
 * @date 2022-01-12
 */
@ApiModel("BizOrderRecord")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizOrderRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private String invoiceNo;

    /** 订单金额 */
    @ApiModelProperty("订单金额")
    @Excel(name = "订单金额")
    private BigDecimal amt;

    /** 原始订单编号 */
    @ApiModelProperty("原始订单编号")
    @Excel(name = "原始订单编号")
    private String originalInvoiceNo;

    /** 数量 */
    @ApiModelProperty("数量")
    @Excel(name = "数量")
    private Long num;

    /** 商品编号 */
    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private String goodCode;

    /** 店铺编号 */
    @ApiModelProperty("店铺编号")
    @Excel(name = "店铺编号")
    private String shopCode;

    /** 店铺名称 */
    @ApiModelProperty("店铺名称")
    @Excel(name = "店铺名称")
    private String shopName;

    /** 渠道编码 */
    @ApiModelProperty("渠道编码")
    @Excel(name = "渠道编码")
    private String channelCode;

    /** 员工编码 */
    @ApiModelProperty("员工编码")
    @Excel(name = "员工编码")
    private String employeeCode;

    /** 折扣率 */
    @ApiModelProperty("折扣率")
    @Excel(name = "折扣率")
    private BigDecimal discount;

    /** 交易类型 */
    @ApiModelProperty("交易类型")
    @Excel(name = "交易类型")
    private String payType;

    /** 交易时间 */
    @ApiModelProperty("交易时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 是否退单（1是 2否） */
    @ApiModelProperty("是否退单（1是 2否）")
    @Excel(name = "是否退单", readConverterExp = "1=是,2=否")
    private String isRefund;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("invoiceNo", getInvoiceNo())
            .append("amt", getAmt())
            .append("originalInvoiceNo", getOriginalInvoiceNo())
            .append("num", getNum())
            .append("shopCode", getShopCode())
            .append("shopName", getShopName())
            .append("channelCode", getChannelCode())
            .append("employeeCode", getEmployeeCode())
            .append("discount", getDiscount())
            .append("payType", getPayType())
            .append("payTime", getPayTime())
            .append("isRefund", getIsRefund())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
