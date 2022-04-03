package com.atguigu.service.impl;

import com.atguigu.dao.UserInfoDao;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.entity.UserInfo;
import com.atguigu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = UserInfoService.class)
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    protected BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }

    @Override
    public Integer findSamePhone(String phone) {
        return userInfoDao.findSamePhone(phone);
    }

    @Override
    public UserInfo login(String phone, String password) {
        return userInfoDao.login(phone,password);
    }

    @Override
    public Integer isDisabled(String phone) {
        return userInfoDao.isDisabled(phone);
    }
}
