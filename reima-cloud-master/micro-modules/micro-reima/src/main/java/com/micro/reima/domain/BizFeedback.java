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
 * 帮助反馈对象 biz_feedback
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizFeedback")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 描述 */
    @ApiModelProperty("描述")
    @Excel(name = "描述")
    private String content;

    /** 图片 */
    @ApiModelProperty("图片")
    @Excel(name = "图片")
    private String urls;

    /** 备注 */
    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String memo;

    /** 处理状态 0未处理 1已处理 */
    @ApiModelProperty("处理状态 0未处理 1已处理")
    @Excel(name = "处理状态 0未处理 1已处理")
    private String procStatus;

    /** 处理意见 */
    @ApiModelProperty("处理意见")
    @Excel(name = "处理意见")
    private String procOpinion;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("urls", getUrls())
            .append("memo", getMemo())
            .append("procStatus", getProcStatus())
            .append("procOpinion", getProcOpinion())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
