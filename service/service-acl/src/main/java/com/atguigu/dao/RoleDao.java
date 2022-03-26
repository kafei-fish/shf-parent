package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Role;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface RoleDao extends BaseDao<Role> {
    List<Role> findAll();

}
