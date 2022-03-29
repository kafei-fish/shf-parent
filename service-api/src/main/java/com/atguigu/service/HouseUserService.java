package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.HouseUser;

import java.util.List;

public interface HouseUserService extends BaseService<HouseUser> {
    List<HouseUser> findAll();

    List<HouseUser> findAllById(Integer id);
}
