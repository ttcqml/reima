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
 * 资讯媒体对象 biz_media
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizMedia")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizMedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 标题 */
    @ApiModelProperty("标题")
    @Excel(name = "标题")
    private String title;

    /** 宣传图片 */
    @ApiModelProperty("宣传图片")
    @Excel(name = "宣传图片")
    private String url;

    /** 点击量 */
    @ApiModelProperty("点击量")
    @Excel(name = "点击量")
    private Long hit;

    /** 主体 */
    @ApiModelProperty("主体")
    @Excel(name = "主体")
    private String content;

    /** 文章链接 */
    @ApiModelProperty("文章链接")
    @Excel(name = "文章链接")
    private String articleUrl;

    /** 类型 0活动 */
    @ApiModelProperty("类型 0活动")
    @Excel(name = "类型 0活动")
    private Long mediaType;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    @Excel(name = "显示顺序")
    private Long dispIdx;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("url", getUrl())
            .append("hit", getHit())
            .append("content", getContent())
            .append("articleUrl", getArticleUrl())
            .append("mediaType", getMediaType())
            .append("dispIdx", getDispIdx())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
