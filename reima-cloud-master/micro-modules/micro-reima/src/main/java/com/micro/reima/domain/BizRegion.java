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
 * 行政区域对象 biz_region
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizRegion")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @ApiModelProperty("$column.columnComment")
    private Long id;

    /** 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0 */
    @ApiModelProperty("行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    @Excel(name = "行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    private Long pid;

    /** 行政区域名称 */
    @ApiModelProperty("行政区域名称")
    @Excel(name = "行政区域名称")
    private String name;

    /** 行政区域类型，1省2市3区县 */
    @ApiModelProperty("行政区域类型，1省2市3区县")
    @Excel(name = "行政区域类型，1省2市3区县")
    private Long type;

    /** 行政区域编码 */
    @ApiModelProperty("行政区域编码")
    @Excel(name = "行政区域编码")
    private Long code;

    /** 热门城市（0否 1是） */
    @ApiModelProperty("热门城市（0否 1是）")
    @Excel(name = "热门城市", readConverterExp = "0=否,1=是")
    private String isHot;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("name", getName())
            .append("type", getType())
            .append("code", getCode())
            .append("isHot", getIsHot())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
