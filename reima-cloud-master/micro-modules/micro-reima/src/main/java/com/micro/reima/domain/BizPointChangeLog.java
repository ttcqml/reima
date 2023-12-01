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
 * 变更记录对象 biz_point_change_log
 * 
 * @author micro
 * @date 2022-04-27
 */
@ApiModel("BizPointChangeLog")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizPointChangeLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 手机号 */
    @ApiModelProperty("手机号")
    @Excel(name = "手机号")
    private String mobile;

    /** 全局流水号 */
    @ApiModelProperty("全局流水号")
    @Excel(name = "全局流水号")
    private String sequen;

    /** 来源 */
    @ApiModelProperty("来源")
    @Excel(name = "来源")
    private String source;

    /** 变更 */
    @ApiModelProperty("变更")
    @Excel(name = "变更")
    private Integer changePoint;

    /** 变更后 */
    @ApiModelProperty("变更后")
    @Excel(name = "变更后")
    private Integer point;

    /** 描述 */
    @ApiModelProperty("描述")
    @Excel(name = "描述")
    private String memo;

    /** 过期时间 */
    @ApiModelProperty("过期时间")
    @Excel(name = "过期时间")
    private String expired;

    /** 时间 */
    @ApiModelProperty("时间")
    @Excel(name = "时间")
    private String created;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("mobile", getMobile())
            .append("sequen", getSequen())
            .append("source", getSource())
            .append("changePoint", getChangePoint())
            .append("point", getPoint())
            .append("memo", getMemo())
            .append("expired", getExpired())
            .append("created", getCreated())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
