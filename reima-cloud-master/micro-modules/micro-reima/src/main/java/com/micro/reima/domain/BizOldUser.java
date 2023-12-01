package com.micro.reima.domain;

import com.micro.common.core.annotation.Excel;
import com.micro.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 老用户信息对象 biz_old_user
 * 
 * @author micro
 * @date 2022-01-24
 */
@ApiModel("BizOldUser")
@EqualsAndHashCode(callSuper = true)
@Data
public class BizOldUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 用户昵称 */
    @ApiModelProperty("用户昵称")
    @Excel(name = "用户昵称")
    private String nickName;

    /** 头像地址 */
    @ApiModelProperty("头像地址")
    private String avatar;

    /** 手机号码 */
    @ApiModelProperty("手机号码")
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别（0男 1女 2未知） */
    @ApiModelProperty("用户性别（0男 1女 2未知）")
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 省份 */
    @ApiModelProperty("省份")
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @ApiModelProperty("城市")
    @Excel(name = "城市")
    private String city;

    /** 生日 */
    @ApiModelProperty("生日")
    @Excel(name = "生日")
    private String birthday;

    /** 积分 */
    @ApiModelProperty("积分")
    private Integer points;

    @ApiModelProperty("是否已授权")
    @Excel(name = "是否已授权")
    private String isTransfer;

    /** 孩子一 */
    @ApiModelProperty("孩子一")
    private String child1;

    /** 孩子二 */
    @ApiModelProperty("孩子二")
    private String child2;

    /** 优惠券 */
    @ApiModelProperty("优惠券")
    private String coupons;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("nickName", getNickName())
                .append("avatar", getAvatar())
                .append("phonenumber", getPhonenumber())
                .append("sex", getSex())
                .append("createBy", getCreateBy())
                .append("province", getProvince())
                .append("createTime", getCreateTime())
                .append("city", getCity())
                .append("updateBy", getUpdateBy())
                .append("birthday", getBirthday())
                .append("updateTime", getUpdateTime())
                .append("points", getPoints())
                .append("child1", getChild1())
                .append("child2", getChild2())
                .append("coupons", getCoupons())
                .toString();
    }
}
