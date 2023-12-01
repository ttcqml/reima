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
 * 领取门店对象 biz_store
 * 
 * @author micro
 * @date 2022-10-06
 */
@ApiModel("BizStore")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 商品主键 */
    @ApiModelProperty("商品主键")
    private Long productId;

    /** 地区 */
    @ApiModelProperty("地区")
    @Excel(name = "地区")
    private String city;

    /** 店铺名称 */
    @ApiModelProperty("店铺名称")
    @Excel(name = "店铺名称")
    private String name;

    /** 店铺地址 */
    @ApiModelProperty("店铺地址")
    @Excel(name = "店铺地址")
    private String addr;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("city", getCity())
            .append("name", getName())
            .append("addr", getAddr())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
