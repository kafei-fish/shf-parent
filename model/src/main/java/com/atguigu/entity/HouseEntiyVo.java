package com.atguigu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 房屋实体类
 */
public class HouseEntiyVo  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //房屋Id
    private Integer houseId;
    //小区ID
    private Integer communityId;
    //房源名称
    private String name;
    //状态
    private Integer status;
    //总价
    private String totalPrice;
    //单位价格
    private String unitPrice;
    //建筑面积
    private String buildArea;
    //套内面积
    private String insideArea;
    //房屋户型
    private String houseTypeName;
    //所在楼层
    private String  floorName;
    //建筑结构
    private String  buildStructureName;
    //房屋朝向
    private String directionName;
    //装修情况
    private String decorationName;
    //房屋用途
    private String houseUseName;
    //梯户比例
    private String  elevatorRatio;
    //挂牌时间
    private Date listingDateString;
    //上次交易
    private Date lastTradeDateString;


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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(String buildArea) {
        this.buildArea = buildArea;
    }

    public String getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(String insideArea) {
        this.insideArea = insideArea;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBuildStructureName() {
        return buildStructureName;
    }

    public void setBuildStructureName(String buildStructureName) {
        this.buildStructureName = buildStructureName;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getDecorationName() {
        return decorationName;
    }

    public void setDecorationName(String decorationName) {
        this.decorationName = decorationName;
    }

    public String getHouseUseName() {
        return houseUseName;
    }

    public void setHouseUseName(String houseUseName) {
        this.houseUseName = houseUseName;
    }

    public String getElevatorRatio() {
        return elevatorRatio;
    }

    public void setElevatorRatio(String elevatorRatio) {
        this.elevatorRatio = elevatorRatio;
    }

    public Date getListingDateString() {
        return listingDateString;
    }

    public void setListingDateString(Date listingDateString) {
        this.listingDateString = listingDateString;
    }

    public Date getLastTradeDateString() {
        return lastTradeDateString;
    }

    public void setLastTradeDateString(Date lastTradeDateString) {
        this.lastTradeDateString = lastTradeDateString;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
}
