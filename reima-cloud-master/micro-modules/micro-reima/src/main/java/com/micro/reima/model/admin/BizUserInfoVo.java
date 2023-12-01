package com.micro.reima.model.admin;

import com.micro.common.core.annotation.Excel;
import com.micro.reima.domain.BizUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("BizUserInfoVo")
public class BizUserInfoVo extends BizUserInfo implements Serializable {
    private static final long serialVersionUID = 5415122213096643767L;
    /** 用户昵称 */
    @ApiModelProperty("用户昵称")
    @Excel(name = "用户昵称")
    private String nickName;
    /** 手机号码 */
    @ApiModelProperty("手机号码")
    @Excel(name = "手机号码")
    private String phonenumber;
    /** 用户性别 0=男,1=女,2=未知*/
    @ApiModelProperty("用户性别 0=男,1=女,2=未知")
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;
    /** 用户头像 */
    @ApiModelProperty("用户头像")
    private String avatar;
    /** 帐号状态（0正常 1停用） */
    @ApiModelProperty("帐号状态（0正常 1停用")
    private String status;
    @ApiModelProperty("省份")
    @Excel(name = "省份")
    private String province;
    @ApiModelProperty("城市")
    @Excel(name = "城市")
    private String city;
    @ApiModelProperty("来源")
    @Excel(name = "来源")
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
