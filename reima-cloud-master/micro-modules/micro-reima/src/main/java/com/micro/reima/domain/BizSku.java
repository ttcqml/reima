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
 * sku对象 biz_sku
 *
 * @author micro
 * @date 2022-01-19
 */
@ApiModel("BizSku")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizSku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 商品代码 */
    @ApiModelProperty("商品代码")
    @Excel(name = "商品代码")
    private String goodCode;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    @Excel(name = "商品名称")
    private String goodName;

    /** 颜色代码 */
    @ApiModelProperty("颜色代码")
    @Excel(name = "颜色代码")
    private String colorCode;

    /** 颜色名称 */
    @ApiModelProperty("颜色名称")
    @Excel(name = "颜色名称")
    private String colorName;

    /** 尺码代码 */
    @ApiModelProperty("尺码代码")
    @Excel(name = "尺码代码")
    private String sizeCode;

    /** 尺码名称 */
    @ApiModelProperty("尺码名称")
    @Excel(name = "尺码名称")
    private String sizeName;

    /** sku */
    @ApiModelProperty("sku")
    @Excel(name = "sku")
    private String skucode;

    /** 商品简称 */
    @ApiModelProperty("商品简称")
    @Excel(name = "商品简称")
    private String goodShortName;

    /** 助记符 */
    @ApiModelProperty("助记符")
    @Excel(name = "助记符")
    private String goodAliasesName;

    /** 启用 1启用0不启用 */
    @ApiModelProperty("启用 1启用0不启用")
    @Excel(name = "启用 1启用0不启用")
    private Long status;

    /** 标准售价 */
    @ApiModelProperty("标准售价")
    @Excel(name = "标准售价")
    private String standardPrice;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastchanged;

    /** 单位代码 */
    @ApiModelProperty("单位代码")
    @Excel(name = "单位代码")
    private String unitCode;

    /** 单位名称 */
    @ApiModelProperty("单位名称")
    @Excel(name = "单位名称")
    private String unitName;

    /** 品牌代码 */
    @ApiModelProperty("品牌代码")
    @Excel(name = "品牌代码")
    private String brandCode;

    /** 大类代码 */
    @ApiModelProperty("大类代码")
    @Excel(name = "大类代码")
    private String categoryCode;

    /** 大类名称 */
    @ApiModelProperty("大类名称")
    @Excel(name = "大类名称")
    private String categoryName;

    /** 中类代码 */
    @ApiModelProperty("中类代码")
    @Excel(name = "中类代码")
    private String mediumCategoryCode;

    /** 中类名称 */
    @ApiModelProperty("中类名称")
    @Excel(name = "中类名称")
    private String mediumCategoryName;

    /** 小类代码 */
    @ApiModelProperty("小类代码")
    @Excel(name = "小类代码")
    private String smallCategoryCode;

    /** 小类名称 */
    @ApiModelProperty("小类名称")
    @Excel(name = "小类名称")
    private String smallCategoryName;

    /** 季节代码 */
    @ApiModelProperty("季节代码")
    @Excel(name = "季节代码")
    private String seaconCode;

    /** 季节名称 */
    @ApiModelProperty("季节名称")
    @Excel(name = "季节名称")
    private String seaconName;

    /** 年度代码 */
    @ApiModelProperty("年度代码")
    @Excel(name = "年度代码")
    private String yearCode;

    /** 年度名称 */
    @ApiModelProperty("年度名称")
    @Excel(name = "年度名称")
    private String yearName;

    /** 波段代码 */
    @ApiModelProperty("波段代码")
    @Excel(name = "波段代码")
    private String bandCode;

    /** 波段名称 */
    @ApiModelProperty("波段名称")
    @Excel(name = "波段名称")
    private String bandName;

    /** 系列代码 */
    @ApiModelProperty("系列代码")
    @Excel(name = "系列代码")
    private String seriesCode;

    /** 系列名称 */
    @ApiModelProperty("系列名称")
    @Excel(name = "系列名称")
    private String seriesName;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("goodCode", getGoodCode())
                .append("goodName", getGoodName())
                .append("colorCode", getColorCode())
                .append("colorName", getColorName())
                .append("sizeCode", getSizeCode())
                .append("sizeName", getSizeName())
                .append("skucode", getSkucode())
                .append("goodShortName", getGoodShortName())
                .append("goodAliasesName", getGoodAliasesName())
                .append("status", getStatus())
                .append("standardPrice", getStandardPrice())
                .append("lastchanged", getLastchanged())
                .append("unitCode", getUnitCode())
                .append("unitName", getUnitName())
                .append("brandCode", getBrandCode())
                .append("categoryCode", getCategoryCode())
                .append("categoryName", getCategoryName())
                .append("mediumCategoryCode", getMediumCategoryCode())
                .append("mediumCategoryName", getMediumCategoryName())
                .append("smallCategoryCode", getSmallCategoryCode())
                .append("smallCategoryName", getSmallCategoryName())
                .append("seaconCode", getSeaconCode())
                .append("seaconName", getSeaconName())
                .append("yearCode", getYearCode())
                .append("yearName", getYearName())
                .append("bandCode", getBandCode())
                .append("bandName", getBandName())
                .append("seriesCode", getSeriesCode())
                .append("seriesName", getSeriesName())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}