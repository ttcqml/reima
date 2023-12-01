package com.micro.reima.model.pos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("MemberInfoVo")
@JsonInclude(value = Include.ALWAYS)
public class MemberInfoVo implements Serializable {

    private static final long serialVersionUID = -7375114471127583339L;
    @ApiModelProperty("会员编码，即会员手机号")
    private String member_code;
    @ApiModelProperty("会员全称")
    private String full_name;
    @ApiModelProperty("会员等级编码")
    private String member_tier_code;
    @ApiModelProperty("会员等级名称")
    private String member_tier_name;
    @ApiModelProperty("固定值：validate,validate,invalidate,froze")
    private String member_status_code;
    @ApiModelProperty("手机号码")
    private String mobile;
    @ApiModelProperty("1=M,2=F,空值则为0")
    private String gender;
    @ApiModelProperty("生日对应年，格式：YYYY")
    private Integer birthday_year;
    @ApiModelProperty("生日对应月，格式：M")
    private Integer birthday_month;
    @ApiModelProperty("生日对应日，格式：D")
    private Integer birthday_day;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("生日,格式：YYYY-MM-DD")
    private Date birthday;
    @ApiModelProperty("注册时间")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00",timezone = "GMT+8")
    private Date register_date;
    @ApiModelProperty("头像")
    private String head_img;

    // 空字段
    private String first_name;
    private String last_name;
    private String mobile_region_code;
    private String email;
    private String register_store_code;
    private String register_store_name;
    private String preferred_store_code;
    private String preferred_store_name;
    private String register_employee_code;
    private String register_employee_name;
    private String register_channel_code;
    private String register_channel_name;
    private String register_brand_code;
    private String register_brand_name;
    private String country_dictionary_code;
    private String country_dictionary_name;
    private String province_dictionary_code;
    private String province_dictionary_name;
    private String city_dictionary_code;
    private String city_dictionary_name;
    private String district_dictionary_code;
    private String district_dictionary_name;
    private String zip_code;
    private String address;
    private String remark;
    private String[] options=new String[]{};
    private String[] dynamic_fields=new String[]{};
    private String[] member_tag=new String[]{};

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMember_tier_code() {
        return member_tier_code;
    }

    public void setMember_tier_code(String member_tier_code) {
        this.member_tier_code = member_tier_code;
    }

    public String getMember_tier_name() {
        return member_tier_name;
    }

    public void setMember_tier_name(String member_tier_name) {
        this.member_tier_name = member_tier_name;
    }

    public String getMember_status_code() {
        return member_status_code;
    }

    public void setMember_status_code(String member_status_code) {
        this.member_status_code = member_status_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getBirthday_year() {
        return birthday_year;
    }

    public void setBirthday_year(Integer birthday_year) {
        this.birthday_year = birthday_year;
    }

    public Integer getBirthday_month() {
        return birthday_month;
    }

    public void setBirthday_month(Integer birthday_month) {
        this.birthday_month = birthday_month;
    }

    public Integer getBirthday_day() {
        return birthday_day;
    }

    public void setBirthday_day(Integer birthday_day) {
        this.birthday_day = birthday_day;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_region_code() {
        return mobile_region_code;
    }

    public void setMobile_region_code(String mobile_region_code) {
        this.mobile_region_code = mobile_region_code;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegister_store_code() {
        return register_store_code;
    }

    public void setRegister_store_code(String register_store_code) {
        this.register_store_code = register_store_code;
    }

    public String getRegister_store_name() {
        return register_store_name;
    }

    public void setRegister_store_name(String register_store_name) {
        this.register_store_name = register_store_name;
    }

    public String getPreferred_store_code() {
        return preferred_store_code;
    }

    public void setPreferred_store_code(String preferred_store_code) {
        this.preferred_store_code = preferred_store_code;
    }

    public String getPreferred_store_name() {
        return preferred_store_name;
    }

    public void setPreferred_store_name(String preferred_store_name) {
        this.preferred_store_name = preferred_store_name;
    }

    public String getRegister_employee_code() {
        return register_employee_code;
    }

    public void setRegister_employee_code(String register_employee_code) {
        this.register_employee_code = register_employee_code;
    }

    public String getRegister_employee_name() {
        return register_employee_name;
    }

    public void setRegister_employee_name(String register_employee_name) {
        this.register_employee_name = register_employee_name;
    }

    public String getRegister_channel_code() {
        return register_channel_code;
    }

    public void setRegister_channel_code(String register_channel_code) {
        this.register_channel_code = register_channel_code;
    }

    public String getRegister_channel_name() {
        return register_channel_name;
    }

    public void setRegister_channel_name(String register_channel_name) {
        this.register_channel_name = register_channel_name;
    }

    public String getRegister_brand_code() {
        return register_brand_code;
    }

    public void setRegister_brand_code(String register_brand_code) {
        this.register_brand_code = register_brand_code;
    }

    public String getRegister_brand_name() {
        return register_brand_name;
    }

    public void setRegister_brand_name(String register_brand_name) {
        this.register_brand_name = register_brand_name;
    }

    public String getCountry_dictionary_code() {
        return country_dictionary_code;
    }

    public void setCountry_dictionary_code(String country_dictionary_code) {
        this.country_dictionary_code = country_dictionary_code;
    }

    public String getCountry_dictionary_name() {
        return country_dictionary_name;
    }

    public void setCountry_dictionary_name(String country_dictionary_name) {
        this.country_dictionary_name = country_dictionary_name;
    }

    public String getProvince_dictionary_code() {
        return province_dictionary_code;
    }

    public void setProvince_dictionary_code(String province_dictionary_code) {
        this.province_dictionary_code = province_dictionary_code;
    }

    public String getProvince_dictionary_name() {
        return province_dictionary_name;
    }

    public void setProvince_dictionary_name(String province_dictionary_name) {
        this.province_dictionary_name = province_dictionary_name;
    }

    public String getCity_dictionary_code() {
        return city_dictionary_code;
    }

    public void setCity_dictionary_code(String city_dictionary_code) {
        this.city_dictionary_code = city_dictionary_code;
    }

    public String getCity_dictionary_name() {
        return city_dictionary_name;
    }

    public void setCity_dictionary_name(String city_dictionary_name) {
        this.city_dictionary_name = city_dictionary_name;
    }

    public String getDistrict_dictionary_code() {
        return district_dictionary_code;
    }

    public void setDistrict_dictionary_code(String district_dictionary_code) {
        this.district_dictionary_code = district_dictionary_code;
    }

    public String getDistrict_dictionary_name() {
        return district_dictionary_name;
    }

    public void setDistrict_dictionary_name(String district_dictionary_name) {
        this.district_dictionary_name = district_dictionary_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getDynamic_fields() {
        return dynamic_fields;
    }

    public void setDynamic_fields(String[] dynamic_fields) {
        this.dynamic_fields = dynamic_fields;
    }

    public String[] getMember_tag() {
        return member_tag;
    }

    public void setMember_tag(String[] member_tag) {
        this.member_tag = member_tag;
    }
}
