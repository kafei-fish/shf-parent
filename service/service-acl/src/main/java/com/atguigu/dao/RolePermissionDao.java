package com.atguigu.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionDao {
    List<Long> byRoleIdfindPermissonId(Long id);

    void deleteByRoleId(Long roleId);

    void saveRoleAndPermissionIs(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);
}
