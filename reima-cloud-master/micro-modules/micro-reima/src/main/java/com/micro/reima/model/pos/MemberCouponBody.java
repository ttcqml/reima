package com.micro.reima.model.pos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("MemberCouponVo")
public class MemberCouponBody implements Serializable {
    private static final long serialVersionUID = 8443646682822600950L;
    @ApiModelProperty("会员编码 即会员手机号")
    private String member_code;
    @ApiModelProperty("分页笔数 默认10")
    private Integer page_size;
    @ApiModelProperty("起始页 默认1")
    private Integer page_no;
    @ApiModelProperty("创建时间从 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_date_from;
    @ApiModelProperty("创建时间至 yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date create_date_to;
    @ApiModelProperty("排序字段 default id")
    private String order_by;
    @ApiModelProperty("是否排序：true或false default false")
    private Boolean asc;

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getPage_no() {
        return page_no;
    }

    public void setPage_no(Integer page_no) {
        this.page_no = page_no;
    }

    public Date getCreate_date_from() {
        return create_date_from;
    }

    public void setCreate_date_from(Date create_date_from) {
        this.create_date_from = create_date_from;
    }

    public Date getCreate_date_to() {
        return create_date_to;
    }

    public void setCreate_date_to(Date create_date_to) {
        this.create_date_to = create_date_to;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public Boolean getAsc() {
        return asc;
    }

    public void setAsc(Boolean asc) {
        this.asc = asc;
    }
}
