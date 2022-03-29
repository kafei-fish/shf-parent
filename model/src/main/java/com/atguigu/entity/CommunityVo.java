package com.atguigu.entity;

import java.io.Serializable;

public class CommunityVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //小区名称
    private String name;


    //区域
    private String areaName;
    //板块
    private String plateName;
    //详细地址
    private String address;
    //建筑年代
    private String buildYears;
    //物业价格
    private String propertyPrice;
    //物业公司
    private String propertyCompany;
    //开发商
    private String developer;
    //楼栋数
    private Integer buildNum;
    private Integer houseNum;
    //均价
    private String averagePrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildYears() {
        return buildYears;
    }

    public void setBuildYears(String buildYears) {
        this.buildYears = buildYears;
    }

    public String getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(String propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(Integer buildNum) {
        this.buildNum = buildNum;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }
}
