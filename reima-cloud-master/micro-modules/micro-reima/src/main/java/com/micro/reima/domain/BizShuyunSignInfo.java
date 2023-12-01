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
 * 数云秘钥授权对象 biz_shuyun_sign_info
 * 
 * @author micro
 * @date 2022-01-08
 */
@ApiModel("BizShuyunSignInfo")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizShuyunSignInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 授权Key */
    @ApiModelProperty("授权Key")
    @Excel(name = "授权Key")
    private String appKey;

    /** 授权秘钥 */
    @ApiModelProperty("授权秘钥")
    @Excel(name = "授权秘钥")
    private String secret;

    /** AppId */
    @ApiModelProperty("AppId")
    @Excel(name = "AppId")
    private String appId;

    /** token */
    @ApiModelProperty("token")
    @Excel(name = "token")
    private String token;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appKey", getAppKey())
            .append("secret", getSecret())
            .append("appId", getAppId())
            .append("token", getToken())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
