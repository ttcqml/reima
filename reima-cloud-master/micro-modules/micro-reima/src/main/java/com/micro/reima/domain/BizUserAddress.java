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
 * 用户地址对象 biz_user_address
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizUserAddress")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizUserAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 收货人名称 */
    @ApiModelProperty("收货人名称")
    @Excel(name = "收货人名称")
    private String name;

    /** 收货人手机号 */
    @ApiModelProperty("收货人手机号")
    @Excel(name = "收货人手机号")
    private String tel;

    /** 省份主键 */
    @ApiModelProperty("省份主键")
    @Excel(name = "省份主键")
    private Long province;

    /** 市区主键 */
    @ApiModelProperty("市区主键")
    @Excel(name = "市区主键")
    private Long city;

    /** 区县主键 */
    @ApiModelProperty("区县主键")
    @Excel(name = "区县主键")
    private Long area;

    /** 详细收货地址 */
    @ApiModelProperty("详细收货地址")
    @Excel(name = "详细收货地址")
    private String address;

    /** 邮政编码 */
    @ApiModelProperty("邮政编码")
    @Excel(name = "邮政编码")
    private String postalCode;

    /** 是否默认 0否 1是 */
    @ApiModelProperty("是否默认 0否 1是")
    @Excel(name = "是否默认 0否 1是")
    private String isDefault;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("tel", getTel())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
            .append("address", getAddress())
            .append("postalCode", getPostalCode())
            .append("isDefault", getIsDefault())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
