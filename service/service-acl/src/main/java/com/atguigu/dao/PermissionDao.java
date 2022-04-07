package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Permission;

import java.util.List;

public interface PermissionDao extends BaseDao<Permission> {
    List<Permission> findAll();


    List<Permission> findMenuPermissionByAdminId(Long adminId);

    Integer deleteByRoleId(Long roleId);

    List<String> findAllByAdminId(Long id);

    List<String> findByAdminId(Long id);
}
