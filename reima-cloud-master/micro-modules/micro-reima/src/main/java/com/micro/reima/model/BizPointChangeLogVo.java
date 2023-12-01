package com.micro.reima.model;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BizPointChangeLogVo implements Serializable {
    /** 手机号 */
    @ApiModelProperty("手机号")
    @Excel(name = "手机号")
    private String mobile;

    /** 全局流水号 */
    @ApiModelProperty("全局流水号")
    @Excel(name = "全局流水号")
    private String sequence;

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
    private String desc;

    /** 过期时间 */
    @ApiModelProperty("过期时间")
    @Excel(name = "过期时间")
    private String expired;

    /** 时间 */
    @ApiModelProperty("时间")
    @Excel(name = "时间")
    private String created;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(Integer changePoint) {
        this.changePoint = changePoint;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
