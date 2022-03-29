package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HouseDetailsDao;
import com.atguigu.entity.CommunityVo;
import com.atguigu.entity.HouseEntiyVo;
import com.atguigu.service.HouseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = HouseDetailsService.class)
@Transactional
public class HouseDetailsServiceImpl extends BaseServiceImpl<HouseEntiyVo> implements HouseDetailsService {
    @Autowired
    private HouseDetailsDao houseDetailsDao;

    @Override
    protected BaseDao<HouseEntiyVo> getEntityDao() {
        return houseDetailsDao;
    }

    @Override
    public HouseEntiyVo findAllHouse(Integer id) {
        return houseDetailsDao.findAllHouse(id);
    }

    @Override
    public CommunityVo findAllcommuntiy(Integer id) {
        return houseDetailsDao.findAllcommuntiy(id);
    }
}
