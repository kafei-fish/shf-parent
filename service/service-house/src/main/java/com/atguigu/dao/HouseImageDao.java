package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseImageDao extends BaseDao<HouseImage> {

    List<HouseImage> findHouseImageByHouseId(@Param("id") Long id, @Param("type") Integer type);
}
