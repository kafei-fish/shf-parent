package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/**
 *
 * @author MyLong
 */
@Transactional
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {

        return   roleDao.findAll();
    }

    @Override
    protected BaseDao<Role> getEntityDao() {
        return roleDao;
    }
}
