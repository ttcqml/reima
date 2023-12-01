package com.micro.reima.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.common.core.annotation.Excel;
import com.micro.common.core.utils.DateUtils;
import com.micro.reima.model.bo.BizUserChildInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户孩子对象 biz_user_child
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizUserChild")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizUserChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @Excel(name = "姓名")
    private String name;

    /** 性别 （0男 1女 2未知） */
    @ApiModelProperty("性别 （0男 1女 2未知）")
    @Excel(name = "性别 ", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 生日 */
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 与孩子关系 */
    @ApiModelProperty("与孩子关系")
    @Excel(name = "与孩子关系")
    private String relationship;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    @Excel(name = "显示顺序")
    private Long dispIdx;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public static BizUserChild buildBizUserChild(BizUserChildInfo bo, Long userId){
        BizUserChild bizUserChild = new BizUserChild();
        bizUserChild.setUserId(userId);
        bizUserChild.setName(bo.getName());
        bizUserChild.setSex(bo.getSex());
        bizUserChild.setDelFlag("0");
        bizUserChild.setCreateTime(DateUtils.getNowDate());
        bizUserChild.setCreateBy("system");
        bizUserChild.setUpdateTime(DateUtils.getNowDate());
        bizUserChild.setUpdateBy("system");
        return bizUserChild;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("relationship", getRelationship())
            .append("dispIdx", getDispIdx())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
