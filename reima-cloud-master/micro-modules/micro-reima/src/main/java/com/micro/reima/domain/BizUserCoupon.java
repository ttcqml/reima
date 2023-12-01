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
 * 用户优惠券对象 biz_user_coupon
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizUserCoupon")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizUserCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户ID */
    @ApiModelProperty("用户ID")
    private Long userId;

    /** 优惠券ID */
    @ApiModelProperty("优惠券ID")
    private Long couponId;

    /** 核销码 */
    @ApiModelProperty("核销码")
    @Excel(name = "核销码")
    private String verificationCode;

    /** 使用状态, 如果是0则未使用,如果是1则已使用,如果是2则已过期,如果是3则已经下架, */
    @ApiModelProperty("使用状态, 如果是0则未使用,如果是1则已使用,如果是2则已过期,如果是3则已经下架,")
    @Excel(name = "使用状态",readConverterExp = "0=未使用,1=已使用,2=已过期,3=已下架")
    private Long status;

    /** 使用时间 */
    @ApiModelProperty("使用时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date usedTime;

    /** 有效期开始时间 */
    @ApiModelProperty("有效期开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 有效期截至时间 */
    @ApiModelProperty("有效期截至时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效期截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("couponId", getCouponId())
            .append("status", getStatus())
            .append("usedTime", getUsedTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
