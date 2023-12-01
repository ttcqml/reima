package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("调整积分接口")
public class ChangePointBo {

    private Long id;
    @ApiModelProperty("手机号")
    private String phonenumber;
    @ApiModelProperty("变更积分 ")
    private Integer changePoint;
    @ApiModelProperty("描述信息 ")
    private String memo;
    @ApiModelProperty("业务来源 CONSUME:消费（负积分） OTHER:其他（可正可负）INIT_POINT:初始化积分(正积分)")
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getChangePoint() {
        return changePoint;
    }

    public void setChangePoint(Integer changePoint) {
        this.changePoint = changePoint;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
