package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("SYChangelogVo")
public class SYChangelogVo implements Serializable {

    private String  created;
    private String  source;
    private Integer  changePoint;
    private Integer  point;
    private String desc;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
}
