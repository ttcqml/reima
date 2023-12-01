package com.micro.reima.domain;

import java.math.BigDecimal;
import com.micro.common.core.annotation.Excel;
import com.micro.common.core.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 积分账户对象 biz_user_acc
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizUserAcc")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizUserAcc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 余额 */
    @ApiModelProperty("余额")
    @Excel(name = "余额")
    private BigDecimal balance;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public static BizUserAcc buildBizUserAcc(Long userId){
        BizUserAcc bizUserAcc = new BizUserAcc();
        bizUserAcc.setUserId(userId);
        bizUserAcc.setBalance(BigDecimal.ZERO);
        bizUserAcc.setDelFlag("0");
        bizUserAcc.setCreateTime(DateUtils.getNowDate());
        bizUserAcc.setCreateBy("system");
        bizUserAcc.setUpdateTime(DateUtils.getNowDate());
        bizUserAcc.setUpdateBy("system");
        return bizUserAcc;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("balance", getBalance())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
