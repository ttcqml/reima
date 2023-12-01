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
 * 优惠券对象 biz_coupon
 * 
 * @author micro
 * @date 2021-12-25
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("BizCoupon")
@Data
public class BizCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    @Excel(name = "优惠券名称")
    private String name;

    /** 优惠券介绍,通常是显示优惠券使用限 */
    @ApiModelProperty("优惠券介绍,通常是显示优惠券使用限")
    @Excel(name = "优惠券介绍,通常是显示优惠券使用限")
    private String breif;

    /** 优惠券标签,例如新人专用 */
    @ApiModelProperty("优惠券标签,例如新人专用")
    private String tag;

    /** 优惠券数量 */
    @ApiModelProperty("优惠券数量")
    @Excel(name = "优惠券数量")
    private Long total;

    /** 优惠金额, */
    @ApiModelProperty("优惠金额,")
    @Excel(name = "优惠金额,")
    private BigDecimal discount;

    /** 最少消费金额才能使用优惠券 */
    @ApiModelProperty("最少消费金额才能使用优惠券")
    @Excel(name = "最少消费金额才能使用优惠券")
    private BigDecimal min;

    /** 用户领券限制数量 */
    @ApiModelProperty("用户领券限制数量")
    private Long receiveLimit;

    /** 优惠券赠送类型,如果是0则通用券,用户领取,如果是1,则是注册赠券,如果是2则是兑换券 */
    @ApiModelProperty("优惠券赠送类型,如果是0则通用券,用户领取,如果是1,则是注册赠券,如果是2则是兑换券")
    private Long type;

    /** 优惠券兑换码 */
    @ApiModelProperty("优惠券兑换码")
    private String code;

    /** 有效时间限制,如果是0,则基于领取时间的有效天数days,如果是1,则start_time和end_time是优惠券有效期 */
    @ApiModelProperty("有效时间限制,如果是0,则基于领取时间的有效天数days,如果是1,则start_time和end_time是优惠券有效期")
    @Excel(name = "有效时间限制",readConverterExp = "0=基于days,1=基于有效期")
    private Long timeType;

    /** 基于领取时间的有效天数days */
    @ApiModelProperty("基于领取时间的有效天数days")
    @Excel(name = "基于领取时间的有效天数days")
    private Long days;

    /** 使用券开始时间 */
    @ApiModelProperty("使用券开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用券开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 使用券截至时间 */
    @ApiModelProperty("使用券截至时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用券截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 兑换所需积分数量 */
    @ApiModelProperty("兑换所需积分数量")
    @Excel(name = "兑换所需积分数量")
    private BigDecimal exPoints;

    /** 优惠券状态,如果是0则是正常可用,如果是1则是过期;如果是2则是下架 */
    @ApiModelProperty("优惠券状态,如果是0则是正常可用,如果是1则是过期;如果是2则是下架")
    @Excel(name = "优惠券状态",readConverterExp = "0=正常,1=已过期,2=已下架")
    private Long status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("breif", getBreif())
            .append("tag", getTag())
            .append("total", getTotal())
            .append("discount", getDiscount())
            .append("min", getMin())
            .append("receiveLimit", getReceiveLimit())
            .append("type", getType())
            .append("code", getCode())
            .append("timeType", getTimeType())
            .append("days", getDays())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
