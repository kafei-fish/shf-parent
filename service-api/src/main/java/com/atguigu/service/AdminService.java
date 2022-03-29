package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.Admin;

import java.util.List;

public interface AdminService extends BaseService<Admin> {
    List<Admin> fiandAll();
}
