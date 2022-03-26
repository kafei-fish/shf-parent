package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.AdminDao;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Transactional
@Service(interfaceClass = AdminService.class)
public class AdminSerciceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Resource
    private AdminDao adminDao;
    @Override
    protected BaseDao<Admin> getEntityDao() {

        return adminDao;
    }
}
