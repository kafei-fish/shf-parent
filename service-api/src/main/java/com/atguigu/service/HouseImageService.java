package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.HouseImage;

import java.util.List;

public interface HouseImageService  extends BaseService<HouseImage> {
    List<HouseImage> findHouseImageByHouseId(Long houseId,Integer type);
}
