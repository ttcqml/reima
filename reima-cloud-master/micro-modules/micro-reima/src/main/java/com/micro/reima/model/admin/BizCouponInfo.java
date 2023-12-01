package com.micro.reima.model.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("BizCouponInfo")
public class BizCouponInfo implements Serializable {
    /** 主键 */
    @ApiModelProperty("主键")
    private Long id;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
