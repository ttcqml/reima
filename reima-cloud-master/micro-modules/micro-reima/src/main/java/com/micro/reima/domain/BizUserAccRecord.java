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
 * 余额明细对象 biz_user_acc_record
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizUserAccRecord")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizUserAccRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 数量 */
    @ApiModelProperty("数量")
    @Excel(name = "数量")
    private BigDecimal amt;

    /** 状态 0正常 1无效 */
    @ApiModelProperty("状态 0正常 1无效")
    @Excel(name = "状态 0正常 1无效")
    private String status;

    /** 备注 */
    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String memo;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public static BizUserAccRecord buildBizUserAccRecord(Long userId,BigDecimal amt,String remark){
        BizUserAccRecord bizUserAccRecord = new BizUserAccRecord();
        bizUserAccRecord.setUserId(userId);
        bizUserAccRecord.setAmt(amt);
        bizUserAccRecord.setRemark(remark);
        bizUserAccRecord.setStatus("0");
        bizUserAccRecord.setDelFlag("0");
        bizUserAccRecord.setCreateTime(DateUtils.getNowDate());
        bizUserAccRecord.setCreateBy("system");
        bizUserAccRecord.setUpdateTime(DateUtils.getNowDate());
        bizUserAccRecord.setUpdateBy("system");
        return bizUserAccRecord;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("amt", getAmt())
            .append("status", getStatus())
            .append("memo", getMemo())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
