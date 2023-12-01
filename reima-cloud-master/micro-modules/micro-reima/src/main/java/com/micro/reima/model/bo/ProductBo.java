package com.micro.reima.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("同步商品类")
public class ProductBo  {
    private Long id;
    @ApiModelProperty("店铺编号")
    private String shopId;
    @ApiModelProperty("商品编号 get_good_list接口good_code")
    private String productId;
    @ApiModelProperty("商品名称 get_good_list接口good_name")
    private String productName;
    @ApiModelProperty("价格 get_good_list接口standard_price")
    private String price;

    private List<ProductChildBo> productChildBos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ProductChildBo> getProductChildBos() {
        return productChildBos;
    }

    public void setProductChildBos(List<ProductChildBo> productChildBos) {
        this.productChildBos = productChildBos;
    }
}
