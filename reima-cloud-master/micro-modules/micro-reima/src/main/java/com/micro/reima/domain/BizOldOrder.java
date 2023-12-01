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
 * 老订单对象 biz_old_order
 * 
 * @author micro
 * @date 2022-02-25
 */
@ApiModel("BizOldOrder")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizOldOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 会员编号 */
    @ApiModelProperty("会员编号")
    @Excel(name = "会员编号")
    private String memberCode;

    /** 颜色代码 */
    @ApiModelProperty("颜色代码")
    @Excel(name = "颜色代码")
    private String colorNum;

    /** 颜色名称 */
    @ApiModelProperty("颜色名称")
    @Excel(name = "颜色名称")
    private String colorName;

    /** 尺码代码 */
    @ApiModelProperty("尺码代码")
    @Excel(name = "尺码代码")
    private String sizeNum;

    /** 尺码名称 */
    @ApiModelProperty("尺码名称")
    @Excel(name = "尺码名称")
    private String sizeName;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private String invoiceNo;

    /** 折扣率 */
    @ApiModelProperty("折扣率")
    @Excel(name = "折扣率")
    private BigDecimal discount;

    /** 消费终端 */
    @ApiModelProperty("消费终端")
    @Excel(name = "消费终端")
    private String shopCode;

    /** 商品编号 */
    @ApiModelProperty("商品编号")
    @Excel(name = "商品编号")
    private String goodCode;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    @Excel(name = "商品名称")
    private String goodName;

    /** 手机号 */
    @ApiModelProperty("手机号")
    @Excel(name = "手机号")
    private String phonenumber;

    /** 数量 */
    @ApiModelProperty("数量")
    @Excel(name = "数量")
    private Long num;

    /** 订单金额 */
    @ApiModelProperty("订单金额")
    @Excel(name = "订单金额")
    private BigDecimal amt;

    /** 商品价格 */
    @ApiModelProperty("商品价格")
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /** 同步时间 */
    @ApiModelProperty("同步时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date syncTime;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("memberCode", getMemberCode())
            .append("colorNum", getColorNum())
            .append("colorName", getColorName())
            .append("sizeNum", getSizeNum())
            .append("sizeName", getSizeName())
            .append("invoiceNo", getInvoiceNo())
            .append("discount", getDiscount())
            .append("shopCode", getShopCode())
            .append("goodCode", getGoodCode())
            .append("goodName", getGoodName())
            .append("phonenumber", getPhonenumber())
            .append("num", getNum())
            .append("amt", getAmt())
            .append("price", getPrice())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("syncTime", getSyncTime())
            .toString();
    }
}
