package com.micro.reima.domain;

import java.math.BigDecimal;
import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 兑换历史对象 biz_exchange_record
 * 
 * @author micro
 * @date 2021-12-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("BizExchangeRecord")

public class BizExchangeRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    @ApiModelProperty("兑换单号")
    @Excel(name = "兑换单号")
    private String orderSn;

    /** 积分数量 */
    @ApiModelProperty("积分数量")
    @Excel(name = "积分数量")
    private BigDecimal amt;

    /** 兑换主键 如果是商品则关联商品主键,如果是优惠券则关联我的优惠券主键 */
    @ApiModelProperty("兑换主键 如果是商品则关联商品主键,如果是优惠券则关联我的优惠券主键")
    private Long exid;

    /** 兑换类型（0兑换商品 1兑换优惠券） */
    @ApiModelProperty("兑换类型（0兑换商品 1兑换优惠券）")
    @Excel(name = "兑换类型", readConverterExp = "0=兑换商品,1=兑换优惠券")
    private String extype;

    /** 待发货 已发货 */
    @ApiModelProperty("待发货 已发货")
    @Excel(name = "待发货 已发货")
    private String state;

    /** 快递公司：顺丰速运 */
    @ApiModelProperty("快递公司：顺丰速运")
    @Excel(name = "快递公司：顺丰速运")
    private String express;

    /** 快递单号：SF-001 */
    @ApiModelProperty("快递单号：SF-001")
    @Excel(name = "快递单号：SF-001")
    private String expressNum;

    /** 收货人名称 */
    @ApiModelProperty("收货人名称")
    @Excel(name = "收货人名称")
    private String name;

    /** 收货人手机号 */
    @ApiModelProperty("收货人手机号")
    @Excel(name = "收货人手机号")
    private String tel;

    /** 详细收货地址 */
    @ApiModelProperty("详细收货地址")
    @Excel(name = "详细收货地址")
    private String address;


    /** 领取方式 1快递 2自提 */
    @ApiModelProperty("领取方式 1快递 2自提")
    @Excel(name = "领取方式 1快递 2自提")
    private String way;

    /** 门店名称 */
    @ApiModelProperty("门店名称")
    @Excel(name = "门店名称")
    private String storeName;

    /** 门店地址 */
    @ApiModelProperty("门店地址")
    @Excel(name = "门店地址")
    private String storeAddr;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("amt", getAmt())
            .append("exid", getExid())
            .append("extype", getExtype())
            .append("state", getState())
            .append("express", getExpress())
            .append("expressNum", getExpressNum())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
