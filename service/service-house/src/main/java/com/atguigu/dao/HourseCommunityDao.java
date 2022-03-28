package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface HourseCommunityDao extends BaseDao<Community> {
    List<Community> findAll();
}
