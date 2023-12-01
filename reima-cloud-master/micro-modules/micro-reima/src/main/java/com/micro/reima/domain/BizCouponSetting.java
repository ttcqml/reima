package com.micro.reima.domain;

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
 * 优惠券入会赠送配置对象 biz_coupon_setting
 * 
 * @author micro
 * @date 2022-03-05
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("BizCouponSetting")
@Data
public class BizCouponSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 优惠券ID */
    @ApiModelProperty("优惠券ID")
    @Excel(name = "优惠券ID")
    private Long couponId;

    /** 是否全量（0仅新会员 1全量会员） */
    @ApiModelProperty("是否全量（0仅新会员 1全量会员）")
    @Excel(name = "是否全量", readConverterExp = "0=仅新会员,1=全量会员")
    private String isAll;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    @Excel(name = "优惠券名称")
    private String name;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 截至时间 */
    @ApiModelProperty("截至时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


}
