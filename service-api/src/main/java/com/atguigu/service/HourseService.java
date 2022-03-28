package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.House;

public interface HourseService extends BaseService<House> {
    Integer byIdUpdateStatus(Integer id, Integer status);
}
