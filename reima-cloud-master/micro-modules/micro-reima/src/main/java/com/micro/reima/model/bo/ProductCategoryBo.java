package com.micro.reima.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("ProductCategoryBo")
public class ProductCategoryBo implements Serializable {

  private static final long serialVersionUID = 5090632307734734211L;


  @ApiModelProperty("类目编码")
  private String categoryCode;

  @ApiModelProperty("类目名称")
  private String categoryName;

  public String getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
