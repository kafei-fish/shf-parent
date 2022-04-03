package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.HouseBroker;
import com.sun.corba.se.pept.broker.Broker;

import java.util.List;

public interface HouseBrokerService extends BaseService<HouseBroker>{
    List<HouseBroker> findAll();

    List<HouseBroker> findAllById(Integer id);
    List<HouseBroker>  getByHouseIdAndBroker(Integer houseId);
}
