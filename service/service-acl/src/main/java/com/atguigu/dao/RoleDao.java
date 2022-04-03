package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Role;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();

    Role findRoleListByAdminId(Long id);

    List<Role> findNotContainsRoleId(List<Long> list);
}
