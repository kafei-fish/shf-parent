package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.House;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Resource
public interface HouseDao extends BaseDao<House> {
    Integer byIdUpdateStatus(@Param("id") Integer id, @Param("status") Integer status);

}
