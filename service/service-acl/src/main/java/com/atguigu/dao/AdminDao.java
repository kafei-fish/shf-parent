package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Admin;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface AdminDao extends BaseDao<Admin> {
    List<Admin> fiandAll();

    List<Long> fianRoleId(Long id);

    List<Long> findRolrNotId(Long id);

    Integer insertRoleAndAdmin(@Param("adminId") Long adminId, @Param("roleId") Long roleId);
    Integer deletebyAdminId(@Param("id") Long adminId);
}
