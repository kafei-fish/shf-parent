package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.HouseBrokerService;
import com.atguigu.service.HouseDetailsService;
import com.sun.corba.se.pept.broker.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = HouseBrokerService.class)
@Transactional
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {
    @Autowired
    private HouseBrokerDao houseBrokerDao;
    @Override
    protected BaseDao<HouseBroker> getEntityDao() {
        return houseBrokerDao;
    }

    @Override
    public List<HouseBroker> findAll() {
        return houseBrokerDao.findAll();
    }

    @Override
    public List<HouseBroker> findAllById(Integer id) {
        return houseBrokerDao.findAllById(id);
    }

    @Override
    public  List<HouseBroker>  getByHouseIdAndBroker(Integer houseId) {
        return houseBrokerDao.getByHouseIdAndBroker(houseId);
    }
}
