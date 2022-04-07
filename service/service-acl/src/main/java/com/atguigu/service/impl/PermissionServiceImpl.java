package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.PermissionDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.Permission;
import com.atguigu.entity.RolePermission;
import com.atguigu.helper.PermissionHelper;
import com.atguigu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    protected BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }
    @Autowired
    private RolePermissionDao rolePermissionDao;
    /**
     * 根据角色id查询权限
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> byRoleIdFindPermission(Long id) {
        //查询出全部权限
        List<Permission> permissionList=permissionDao.findAll();
        //通过角色id查询出绝色对应的权限

        List<Long> permissionId=rolePermissionDao.byRoleIdfindPermissonId(id);
        //提前设置zNodes节点，用来接收数据
        List<Map<String,Object>> zNodes=new ArrayList<>();
        //变量permission，将他们放入道map集合中
        for (Permission permission : permissionList) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            //判断用户的权限Id列表是否包含权限Id，如果有就设置他为选中
            if(permissionId.contains(permission.getId())){
                map.put("checked",true);
            }
            //将map集合设置道znodes节点中去
            zNodes.add(map);
        }
        return zNodes;
    }

    /**
     *
     * 在进行添加时，首先对角色原有的id删除，然后在将roldId和权限Id插入道数据库中
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Override
    public Integer saveRoleByPermissionIds(Long roleId, Long[] permissionIds) {
        rolePermissionDao.deleteByRoleId(roleId);
        //然后在进行添加
        for (Long permissionId : permissionIds) {
            rolePermissionDao.saveRoleAndPermissionIs(roleId,permissionId);
        }

        return 1;
    }

    @Override
    public List<Permission> findMenuPermissionByAdminId(Long adminId) {
        List<Permission> permissions=null;
        if(adminId ==1){
            //说明是顶级管理员，查询全部菜单
           permissions = permissionDao.findAll();
        }else {
            permissions=permissionDao.findMenuPermissionByAdminId(adminId);
        }
        return PermissionHelper.bulid(permissions);
    }

    @Override
    public List<Permission> findMenu() {
        return permissionDao.findAll();
    }

    @Override
    public List<String> findCodeListByAdminId(Long id) {
        List<String> codeList=null;
        if(id==1){
            //查询全部
            codeList=permissionDao.findAllByAdminId(id);
        }else{
            //就根据id查询
            codeList=permissionDao.findByAdminId(id);
        }

        return codeList;
    }


}
