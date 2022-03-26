package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HourseCommunityDao;
import com.atguigu.entity.House;
import com.atguigu.service.HourseCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(interfaceClass = HourseCommunityService.class)
@Transactional
public class HourseCommunityServiceImpl extends BaseServiceImpl<House> implements HourseCommunityService {
    @Resource
    private HourseCommunityDao communityDao;
    @Override
    protected BaseDao<House> getEntityDao() {
        return communityDao;
    }
}
