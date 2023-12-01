package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("商品SKU")
public class ProductSkuPo {
    @ApiModelProperty("颜色编码")
    private String color_code;
    @ApiModelProperty("颜色名称")
    private String color_name;
    @ApiModelProperty("颜色编码")
    private String size_code;
    @ApiModelProperty("颜色名称")
    private String size_name;
    @ApiModelProperty("颜色编码")
    private String skucode;
    @ApiModelProperty("颜色名称")
    private String standard_price;

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getSize_code() {
        return size_code;
    }

    public void setSize_code(String size_code) {
        this.size_code = size_code;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public String getSkucode() {
        return skucode;
    }

    public void setSkucode(String skucode) {
        this.skucode = skucode;
    }

    public String getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(String standard_price) {
        this.standard_price = standard_price;
    }
}
