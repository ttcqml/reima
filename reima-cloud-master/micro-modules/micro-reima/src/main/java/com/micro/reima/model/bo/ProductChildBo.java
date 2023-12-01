package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("同步商品SKU类")
public class ProductChildBo {
    @ApiModelProperty("SKU详情 get_sku_list接口\n" +
            "\"颜色:\"+color_name+\";\"+\"尺码:\"+size_name")
    private String skuDetail;
    @ApiModelProperty("商品SKU get_sku_list接口skucode")
    private String skuId;
    @ApiModelProperty("SKU价格 get_sku_list接口standard_price")
    private String skuPrice;



    public String getSkuDetail() {
        return skuDetail;
    }

    public void setSkuDetail(String skuDetail) {
        this.skuDetail = skuDetail;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }
}
