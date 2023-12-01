package com.micro.reima.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("SimpleBannerVo")
public class SimpleBannerVo implements Serializable {

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 标题 */
  @ApiModelProperty("标题")
  private String title;

  /** 图片 */
  @ApiModelProperty("图片")
  private String url;

  /** 关联内容 */
  @ApiModelProperty("关联内容")
  private String link;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
