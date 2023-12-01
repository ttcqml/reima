package com.micro.reima.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.common.core.annotation.Excel;
import com.micro.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@ApiModel("BizSpu")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizSpu extends BaseEntity
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

    /** 季节代码 */
    @ApiModelProperty("季节代码")
    @Excel(name = "季节代码")
    private String seaconCode;

    /** 年度代码 */
    @ApiModelProperty("年度代码")
    @Excel(name = "年度代码")
    private String yearCode;

    /** 波段代码 */
    @ApiModelProperty("波段代码")
    @Excel(name = "波段代码")
    private String bandCode;

    /** 系列代码 */
    @ApiModelProperty("系列代码")
    @Excel(name = "系列代码")
    private String seriesCode;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


}