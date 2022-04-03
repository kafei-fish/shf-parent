package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.AdminDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author MyLong
 */
@Transactional
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Autowired
    private AdminDao adminDao;
    @Override
    public List<Role> findAll() {

        return   roleDao.findAll();
    }

    /**
     * 查询用户包含的角色
     * @param id
     * @return
     */
    @Override
    public List<Role> findRoleListByAdminId(Long id) {
        //查询admin_role表中adminId包含的角色
        List<Long> roleIds = adminDao.fianRoleId(id);

        //如果有就查询出对应有的角色
        List<Role> roleList=new ArrayList<>();
        for (Long roleId : roleIds) {
            Role role = roleDao.findRoleListByAdminId(roleId);
            roleList.add(role);
        }
        return roleList;
    }

    /**
     * 查询用户不包含角色
     * @param id 用户id
     * @return
     */
    @Override
    public List<Role> findNotRoleListByAdminId(Long id){
        //用来接收用户角色
        List<Role> roleList=null;
        //查询出用户拥有角色id
        List<Long> roleIds=adminDao.fianRoleId(id);
        //如果用户没有角色就查询全部
        if(roleIds.size()==0){
            return roleDao.findAll();
        }
        List<Long> list=new ArrayList<>();
        List<Role> roleList1 =null;
        //将用户拥有的角色Id进行遍历
        for (Long roleId : roleIds) {

            //通过用户角色Id查询出用户拥有的角色
            Role role = roleDao.findRoleListByAdminId(roleId);
            //如果当前用户拥有角色
            if(roleIds.contains(role.getId())){
                //查询出不包含role.getId的角色
                list.add(role.getId());
            //

            }
            roleList1=roleDao.findNotContainsRoleId(list);
            //如果没有将添加到集合中

        }
        System.out.println(roleList1);
        return roleList1;
    }

    @Override
    protected BaseDao<Role> getEntityDao() {
        return roleDao;
    }
}
