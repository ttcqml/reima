package com.micro.reima.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.common.core.annotation.Excel;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.IdUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.micro.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户对象 biz_user_info
 * 
 * @author micro
 * @date 2021-12-25
 */
@ApiModel("BizUserInfo")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户主键 */
    @ApiModelProperty("用户主键")
    @Excel(name = "用户主键")
    private Long userId;

    /** 会员等级 0普通 1会员 */
    @ApiModelProperty("会员等级 0普通 1会员")
//    @Excel(name = "会员等级 0普通 1会员")
    private String vip;

    /** 会员码 */
    @ApiModelProperty("会员码")
//    @Excel(name = "会员码")
    private String vipCode;

    /** 会员序列号 */
    @ApiModelProperty("会员序列号")
//    @Excel(name = "会员序列号")
    private String serialNum;

    /** 生日 */
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 孩子数量 */
    @ApiModelProperty("孩子数量")
    @Excel(name = "孩子数量")
    private Long childNum;

    /** 省份 */
    @ApiModelProperty("省份")
//    @Excel(name = "省份")
    private Long provinceId;

    /** 城市 */
    @ApiModelProperty("城市")
//    @Excel(name = "城市")
    private Long cityId;

    /** 偏好门店 */
    @ApiModelProperty("偏好门店")
//    @Excel(name = "偏好门店")
    private Long preferShopId;

    /** 邀请码 */
    @ApiModelProperty("邀请码")
//    @Excel(name = "邀请码")
    private String inviteCode;

    /** 备注 */
    @ApiModelProperty("备注")
//    @Excel(name = "备注")
    private String memo;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    public static BizUserInfo buildBizUserInfo(Long userId){
        BizUserInfo bizUserInfo = new BizUserInfo();
        bizUserInfo.setUserId(userId);
        bizUserInfo.setVip("0");
        // 生成会员码
        bizUserInfo.setVipCode(IdUtils.getRandomCode("V"));
        // 生成邀请码
        bizUserInfo.setInviteCode(IdUtils.getRandomInviteCode());
        bizUserInfo.setMemo("自动创建");
        bizUserInfo.setDelFlag("0");
        bizUserInfo.setCreateTime(DateUtils.getNowDate());
        bizUserInfo.setCreateBy("system");
        bizUserInfo.setUpdateTime(DateUtils.getNowDate());
        bizUserInfo.setUpdateBy("system");
        return bizUserInfo;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("vip", getVip())
            .append("vipCode", getVipCode())
            .append("birthday", getBirthday())
            .append("childNum", getChildNum())
            .append("provinceId", getProvinceId())
            .append("cityId", getCityId())
            .append("preferShopId", getPreferShopId())
            .append("inviteCode", getInviteCode())
            .append("memo", getMemo())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
