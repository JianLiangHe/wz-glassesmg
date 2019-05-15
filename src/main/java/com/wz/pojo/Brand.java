package com.wz.pojo;

import java.util.Date;

public class Brand {
    private Integer id;

    private String name;

    private String logoUrl;

    private Integer status;

    private Date gmtCreate;

    private Integer brandTypeId;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public Integer getBrandTypeId() {
        return brandTypeId;
    }

    public void setBrandTypeId(Integer brandTypeId) {
        this.brandTypeId = brandTypeId;
    }
}