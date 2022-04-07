package com.atguigu.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.Base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Role;
import com.atguigu.result.Result;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.atguigu.uitl.CastUtil;
import com.atguigu.uitl.OOSUitil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private  static final String ADMIN_PAGE="admin/index";
    private  static final String ADMIN_CREATE="admin/create";
    private  static final String ADMIN_PAGE_EDIT="admin/edit";
    private  static final String ADMIN_PAGE_UPLOAD="admin/upload";
    private static final String ADMIN_PAGE_ASSIGNSHOW="admin/assignShow";
    private  static final String PAGE_SUCCESS = "common/successPage";

    @Reference
    private AdminService adminService;
    @Reference
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PreAuthorize("hasAuthority('role.create')")
    @GetMapping("/create")
    public String carete(){
        return ADMIN_CREATE;
    }

    //分页查询全部员工
    @RequestMapping
    public String findAll(ModelMap modelMap, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        PageInfo<Admin> page = adminService.findPage(filters);
        modelMap.put("page",page);
        modelMap.put("filters",filters);
        return ADMIN_PAGE;
    }

    /**
     * 通过Id来获取要修改的用户
     * @param id 用户id
     * @param modelMap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getById(@PathVariable("id") Integer id,ModelMap modelMap){
        Admin admin = adminService.getById(id);
        modelMap.put("admin",admin);
        return ADMIN_PAGE_EDIT;
    }

    /**
     * 添加用户
     * @param admin
     * @return
     */
    @PostMapping("/save")
    public String  save(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Integer insert = adminService.insert(admin);
        return PAGE_SUCCESS;
    }
    /**
     * 修改用户
     * @param admin
     * @return
     */
    @PostMapping("/update")
    public String update(Admin admin){
        Integer update = adminService.update(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 根据Id删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Integer delete = adminService.delete(id);
        return "redirect:/admin/";
    }

    @GetMapping("createUpload/{id}")
    public String getCreateUpload(@PathVariable("id") Integer id,ModelMap map){
        map.put("id",id);
        return ADMIN_PAGE_UPLOAD;
    }
    @PostMapping("upload/{id}")
    public String uploadImage(@RequestParam("file")MultipartFile file,@PathVariable("id") Long id) throws IOException {
        String filename = file.getOriginalFilename();
        String newName= UUID.randomUUID().toString()+filename;
        String url="http://lxiaol.xyz/"+newName;
        OOSUitil.uploadFile(file.getBytes(),newName);
        Admin admin=new Admin();
        admin.setHeadUrl(url);
        admin.setId(id);

        adminService.update(admin);
        return PAGE_SUCCESS;
    }
//    @RequestMapping("")
//    public String updateRole(@PathVariable("id") Integer id,ModelMap map){
//        map.put("adminId",id);
//        return "admin/assignShow";
//    }

    /**
     * 回显角色
     * @param id
     * @param map
     * @return
     */
    @GetMapping("updateRole/{id}")
    public String getRoleAndByAdminId(@PathVariable("id") Long id ,ModelMap map){
//
//        List<Role> assginRoleList = roleService.findRoleListByAdminId(id);
//        List<Role> notRoleListByAdminId = roleService.findNotRoleListByAdminId(id);
//        //在用户点击分配角色时，要得到两种数据，一种数据是未选择的数据，一种是已经选择的数据，
//        //根据用户id首先查询用户拥有的角色，如果用户没有拥有角色，那么直接查出全部角色，进行分配，如果查出有角色就将除了了其有的角色全部返回
//        //当用户进入之后，将角色移动道已选择之后，那我们对应的要将其未选择的数据减少
//
//        List<Admin> admins = adminService.fiandAll();
        List<Role> roles = roleService.findAll();
        //获取拥有角色的id

        //对角色进行分类
        List<Role> noAssginRoleList = new ArrayList<>();
        List<Role> assginRoleList = new ArrayList<>();
        //对角色进行分类
        List<Long> roleId = adminService.fianRoleId(id);
        for (Role role : roles) {
            if(roleId.contains(role.getId())){
                assginRoleList.add(role);
            }else {
                noAssginRoleList.add(role);
            }
        }
        map.put("assginRoleList",assginRoleList);
        map.put("noAssginRoleList",noAssginRoleList);
        map.put("adminId",id);
        return ADMIN_PAGE_ASSIGNSHOW;
    }

    /**
     * 开始进行分配权限
     * @param adminId
     * @param roleIds
     * @return
     */
    @PostMapping("assignRole")
    public String assignRole(@RequestParam("adminId") Long adminId,@RequestParam("roleIds") Long[] roleIds){

        //在进行添加时要判断是否存在相同的权限的id，将已选择中的id取出来，然后与roleIds中id进行比较如果不同就进行添加，
        //将得到的数据进行切分，得到要将Aamin分配的role的对象
        //整体思路
        /**
         * 首先我们得到roles的数据将其进行遍历得到roledId得到id之后我们就开始进行添加
         * 但是在添加之前我们要进行判断用户是在添加还在删除，首先将要添加的roldsId与前端传来的id进行比较，如果要添加的id中有当前拥有的id，那么就不添加，如果没有就进行添加
         * 这是添加的逻辑，
         * 如果roleds与用户用户roleds相同就不进行添加
         * 如果不相同就，判断是比原来rodes多还是少
         * 如果多，就找到多的roles进行添加
         * 如果少就找到将剩下的id进行删除
         */

            //将roleId和adminId插入道admin_role表

            Integer flag=adminService.insertIntoRoleAndAdmin(adminId,roleIds);

        return PAGE_SUCCESS;
    }
}
