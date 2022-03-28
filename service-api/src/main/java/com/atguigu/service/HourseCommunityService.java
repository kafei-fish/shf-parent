package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;

import java.util.List;

public interface HourseCommunityService extends BaseService<Community> {

    List<Community> findAll();
}
