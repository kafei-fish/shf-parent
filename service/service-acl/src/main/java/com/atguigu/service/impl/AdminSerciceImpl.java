package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.AdminDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Role;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.atguigu.uitl.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Transactional
@Service(interfaceClass = AdminService.class)
public class AdminSerciceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleService roleService;
    @Override
    protected BaseDao<Admin> getEntityDao() {

        return adminDao;
    }

    @Override
    public List<Admin> fiandAll() {
        return adminDao.fiandAll();
    }

    @Override
    public Integer insertIntoRoleAndAdmin(Long adminId,  Long[] roles) {
//
//       List<Role> roles= roleService.findRoleListByAdminId(adminId);
//
//        //首先获取现在用户拥有的角色Id
//        List<Long> roleId1 = adminDao.fianRoleId(adminId);
//        if(!roleId1.contains(roleId)){
//            adminDao.insertRoleAndAdmin(adminId,roleId);
//        }
        //roled用户拥有的角色
        //roles用户要进行修改角色，是删除还是添加，该怎么选择
        //首先遍历用户拥有的角色
        adminDao.deletebyAdminId(adminId);

        for (Long role : roles) {
            if(StringUtils.isEmpty(role)) continue;
            adminDao.insertRoleAndAdmin(adminId,role);
        }

        return 0;
    }

    @Override
    public List<Long> fianRoleId(Long id) {
        return adminDao.fianRoleId(id);
    }

    @Override
    public Admin findId(Long adminId) {

        return adminDao.findById(adminId);
    }

    @Override
    public Admin fianByAdminName(String adminName) {
        return adminDao.fianByAdminName(adminName);
    }

    @Override
    public PageInfo<Admin> findPage(Map<String, Object> filters) {
        int pageNum= CastUtil.castInt(filters.get("pageNum"),1);
        int pageSize=CastUtil.castInt(filters.get("pageSize"),3);

        PageHelper.startPage(pageNum,pageSize);

        Page<Admin> page = getEntityDao().findPage(filters);

        List<Admin> result = page.getResult();
        for (Admin admin : result) {
            //通过adminId查询出对应的角色id
            //通过用户角色找到用户id，在通过角色id查询出角色

            List<Long> roleIdLists=adminDao.fianRoleId(admin.getId());
            List<Role> roleList=new ArrayList<>();
            for (Long roleId : roleIdLists) {
                Role role= roleDao.findRoleListByAdminId(roleId);
                roleList.add(role);
            }
            admin.setRoleList(roleList);

        }
        return new PageInfo<Admin>(page,10);
    }
}
