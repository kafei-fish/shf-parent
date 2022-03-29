package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Admin;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface AdminDao extends BaseDao<Admin> {
    List<Admin> fiandAll();
}
