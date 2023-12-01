package com.micro.reima.model.pos;

import java.io.Serializable;
import java.util.Date;

public class ProductEntity implements Serializable {
    private static final long serialVersionUID = -7722414558737344193L;
    private String good_code;
    private String good_name;
    private String good_short_name;
    private String good_aliases_name;
    private Long status;
    private String standard_price;
    private String unit_code;
    private String unit_name;
    private String brand_code;
    private String category_code;
    private String seacon_code;
    private String year_code;
    private String band_code;
    private String series_code;

    public String getGood_code() {
        return good_code;
    }

    public void setGood_code(String good_code) {
        this.good_code = good_code;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getGood_short_name() {
        return good_short_name;
    }

    public void setGood_short_name(String good_short_name) {
        this.good_short_name = good_short_name;
    }

    public String getGood_aliases_name() {
        return good_aliases_name;
    }

    public void setGood_aliases_name(String good_aliases_name) {
        this.good_aliases_name = good_aliases_name;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(String standard_price) {
        this.standard_price = standard_price;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getBrand_code() {
        return brand_code;
    }

    public void setBrand_code(String brand_code) {
        this.brand_code = brand_code;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getSeacon_code() {
        return seacon_code;
    }

    public void setSeacon_code(String seacon_code) {
        this.seacon_code = seacon_code;
    }

    public String getYear_code() {
        return year_code;
    }

    public void setYear_code(String year_code) {
        this.year_code = year_code;
    }

    public String getBand_code() {
        return band_code;
    }

    public void setBand_code(String band_code) {
        this.band_code = band_code;
    }

    public String getSeries_code() {
        return series_code;
    }

    public void setSeries_code(String series_code) {
        this.series_code = series_code;
    }
}
