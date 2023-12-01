package com.micro.reima.model.vo;

import com.micro.common.core.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class BizStoreExport implements Serializable {

    /** 地区 */
    @ApiModelProperty("地区")
    @Excel(name = "地区")
    private String city;

    /** 店铺名称 */
    @ApiModelProperty("店铺名称")
    @Excel(name = "店铺名称")
    private String name;

    /** 店铺地址 */
    @ApiModelProperty("店铺地址")
    @Excel(name = "店铺地址")
    private String addr;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
