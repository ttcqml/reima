package com.micro.reima.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel("SimpleMediaVo")
public class SimpleMediaVo implements Serializable {

  private static final long serialVersionUID = 2289839139170977615L;

  /** 主键 */
  @ApiModelProperty("主键")
  private Long id;

  /** 标题 */
  @ApiModelProperty("标题")
  private String title;

  /** 宣传图片 */
  @ApiModelProperty("宣传图片")
  private String url;

  /** 点击量 */
  @ApiModelProperty("点击量")
  private Long hit;

  /** 文章链接 */
  @ApiModelProperty("文章链接")
  private String articleUrl;

  /** 创建时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty("创建时间 后端自动生成")
  private Date createTime;

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

  public Long getHit() {
    return hit;
  }

  public void setHit(Long hit) {
    this.hit = hit;
  }

  public String getArticleUrl() {
    return articleUrl;
  }

  public void setArticleUrl(String articleUrl) {
    this.articleUrl = articleUrl;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
