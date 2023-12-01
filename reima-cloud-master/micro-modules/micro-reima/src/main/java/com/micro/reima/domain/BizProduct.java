package com.micro.reima.domain;

import java.math.BigDecimal;
import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import javax.validation.constraints.Max;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品信息对象 biz_product
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizProduct")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    @Excel(name = "商品名称")
    @Max(value = 64,message = "商品名称过长")
    private String name;

    /** 数量 */
    @ApiModelProperty("数量")
    @Excel(name = "数量")
    private Long total;

    /** 商品主图 */
    @ApiModelProperty("商品主图")
    @Excel(name = "商品主图")
    private String url;

    /** 商品宣传图片列表，采用JSON数组格式 */
    @ApiModelProperty("商品宣传图片列表，采用JSON数组格式")
    @Excel(name = "商品宣传图片列表，采用JSON数组格式")
    private String gallery;

    /** 商品简介 */
    @ApiModelProperty("商品简介")
    @Excel(name = "商品简介")
    @Max(value = 128,message = "商品名称过长")
    private String brief;

    /** 商品详情 */
    @ApiModelProperty("商品详情")
    @Excel(name = "商品详情")
    private String detail;

    /** 商品价格 */
    @ApiModelProperty("商品价格")
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 商品单位 */
    @ApiModelProperty("商品单位")
    @Excel(name = "商品单位")
    private String unit;

    /** 是否上架 0否 1是 */
    @ApiModelProperty("是否上架 0否 1是")
    @Excel(name = "是否上架 0否 1是")
    private String isOnSale;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    @Excel(name = "显示顺序")
    private Long dispIdx;

    /** 配送方式 1快递 2自提 */
    @ApiModelProperty("配送方式 1快递 2自提")
    private String way;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("url", getUrl())
            .append("gallery", getGallery())
            .append("brief", getBrief())
            .append("detail", getDetail())
            .append("price", getPrice())
            .append("unit", getUnit())
            .append("isOnSale", getIsOnSale())
            .append("dispIdx", getDispIdx())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
