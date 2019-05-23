package com.wz.pojo;

import java.util.Date;

public class BrandSeries {
    private Integer id;

    private String name;

    private Integer status;

    private Date gmtCreate;

    private Integer brandId;

    private String screenJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getScreenJson() {
        return screenJson;
    }

    public void setScreenJson(String screenJson) {
        this.screenJson = screenJson;
    }
}