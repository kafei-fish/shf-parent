package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.PermissionDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    /**
     * 根据角色id查询权限
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> byRoleIdFindPermission(Long id) {
        //首先查询出全部权限
        List<Permission> permissionList=permissionDao.findAll();
        //通过角色id查询出权限id，在使用权限id进行查询
        List<Long> permissionId=rolePermissionDao.byRoleIdfindPermissonId(id);
        List<Map<String,Object>> zNodes=new ArrayList<>();
        for (Permission permission : permissionList) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            if(permissionId.contains(permission.getId())){
                map.put("checked",true);
            }
            zNodes.add(map);
        }
        return zNodes;
    }

    @Override
    public Integer saveRoleByPermissionIds(Long roleId, Long[] permissionIds) {
        rolePermissionDao.deleteByRoleId(roleId);
        //然后在进行添加
        for (Long permissionId : permissionIds) {
            rolePermissionDao.saveRoleAndPermissionIs(roleId,permissionId);
        }

        return 1;
    }
}
