package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.HouseUser;

import java.util.List;

public interface HouseUserDao extends BaseDao<HouseUser> {
    List<HouseUser> findAll();

    List<HouseUser> findAllById(Integer id);
}
