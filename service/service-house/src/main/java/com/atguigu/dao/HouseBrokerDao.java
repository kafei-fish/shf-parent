package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.Base.BaseService;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseUser;

import java.util.List;

public interface HouseBrokerDao extends BaseDao<HouseBroker> {
    List<HouseBroker> findAll();

    List<HouseBroker> findAllById(Integer id);
}
