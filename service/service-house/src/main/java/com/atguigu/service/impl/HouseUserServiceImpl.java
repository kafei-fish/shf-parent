package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HouseUserDao;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(interfaceClass = HouseUserService.class)
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {
    @Autowired
    private HouseUserDao houseUserDao;
    @Override
    protected BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }

    @Override
    public List<HouseUser> findAll() {
        return houseUserDao.findAll();
    }

    @Override
    public List<HouseUser> findAllById(Integer id) {
        return houseUserDao.findAllById(id);
    }
}
