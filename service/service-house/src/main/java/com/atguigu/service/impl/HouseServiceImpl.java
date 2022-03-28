package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
import com.atguigu.service.HourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Transactional
@Service(interfaceClass = HourseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HourseService {
    @Resource
    private HouseDao houseDao;
    @Override
    protected BaseDao<House> getEntityDao() {
        return houseDao;
    }

    @Override
    public Integer byIdUpdateStatus(Integer id, Integer status) {
        return houseDao.byIdUpdateStatus(id,status);
    }
}
