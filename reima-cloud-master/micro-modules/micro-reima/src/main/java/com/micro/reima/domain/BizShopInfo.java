package com.micro.reima.domain;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店铺对象 biz_shop_info
 * 
 * @author micro
 * @date 2022-01-08
 */
@ApiModel("BizShopInfo")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizShopInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 店铺ID */
    @ApiModelProperty("店铺ID")
    @Excel(name = "店铺ID")
    private String shopId;

    /** platCode */
    @ApiModelProperty("platCode")
    @Excel(name = "platCode")
    private String platCode;

    /** 批次号 */
    @ApiModelProperty("批次号")
    @Excel(name = "批次号")
    private String cardPlanId;

    /** 店铺名称 */
    @ApiModelProperty("店铺名称")
    @Excel(name = "店铺名称")
    private String shopName;

    /** accountId */
    @ApiModelProperty("accountId")
    @Excel(name = "accountId")
    private String accountId;

    /** 地址 */
    @ApiModelProperty("地址")
    @Excel(name = "地址")
    private String address;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    @Excel(name = "联系电话")
    private String telephone;

    /** 省份 */
    @ApiModelProperty("省份")
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @ApiModelProperty("城市")
    @Excel(name = "城市")
    private String city;

    /** 地区 */
    @ApiModelProperty("地区")
    @Excel(name = "地区")
    private String county;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    @Excel(name = "联系电话")
    private String phone;

    /** 邮件 */
    @ApiModelProperty("邮件")
    @Excel(name = "邮件")
    private String email;

    /** 邮编 */
    @ApiModelProperty("邮编")
    @Excel(name = "邮编")
    private String postcode;

    /** 组织代码 */
    @ApiModelProperty("组织代码")
    @Excel(name = "组织代码")
    private String orgCode;

    /** 组织名称 */
    @ApiModelProperty("组织名称")
    @Excel(name = "组织名称")
    private String orgName;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("platCode", getPlatCode())
            .append("cardPlanId", getCardPlanId())
            .append("shopName", getShopName())
            .append("accountId", getAccountId())
            .append("address", getAddress())
            .append("telephone", getTelephone())
            .append("province", getProvince())
            .append("city", getCity())
            .append("county", getCounty())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("postcode", getPostcode())
            .append("orgCode", getOrgCode())
            .append("orgName", getOrgName())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
