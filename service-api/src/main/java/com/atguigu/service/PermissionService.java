package com.atguigu.service;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    List<Map<String, Object>> byRoleIdFindPermission(Long id);

    Integer saveRoleByPermissionIds(Long roleId, Long[] permissionIds);
}
