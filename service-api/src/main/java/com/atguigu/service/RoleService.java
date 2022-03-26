package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    List<Role> findAll();

}
