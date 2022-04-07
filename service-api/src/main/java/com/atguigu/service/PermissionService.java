package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission> {
    List<Map<String, Object>> byRoleIdFindPermission(Long id);

    Integer saveRoleByPermissionIds(Long roleId, Long[] permissionIds);

    /**
     * 根据管理员id查询其对应的菜单
     * @param adminId 管理员Id
     * @return 菜单列表
     */
    List<Permission>  findMenuPermissionByAdminId(Long adminId);

    List<Permission> findMenu();

    List<String> findCodeListByAdminId(Long id);
}
