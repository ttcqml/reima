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
 * 支付明细对象 biz_pay_detail
 * 
 * @author micro
 * @date 2022-01-12
 */
@ApiModel("BizPayDetail")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizPayDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 订单主键 */
    @ApiModelProperty("订单主键")
    @Excel(name = "订单主键")
    private Long orderId;

    /** 支付类型 */
    @ApiModelProperty("支付类型")
    @Excel(name = "支付类型")
    private String payType;

    /** 实际金额 */
    @ApiModelProperty("实际金额")
    @Excel(name = "实际金额")
    private BigDecimal amt;

    /** 折扣率 */
    @ApiModelProperty("折扣率")
    @Excel(name = "折扣率")
    private BigDecimal discount;

    /** 源号码 */
    @ApiModelProperty("源号码")
    @Excel(name = "源号码")
    private String sourceNo;

    /** 核销码 */
    @ApiModelProperty("核销码")
    @Excel(name = "核销码")
    private String verificationCode;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("payType", getPayType())
            .append("amt", getAmt())
            .append("discount", getDiscount())
            .append("sourceNo", getSourceNo())
            .append("verificationCode", getVerificationCode())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
