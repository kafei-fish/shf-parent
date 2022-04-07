package com.atguigu.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Permission;
import com.atguigu.result.Result;
import com.atguigu.service.PermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    private static final String PAGE_PERMISSION_SHOW="role/assginShow";
    private  static final String PAGE_SUCCESS = "common/successPage";
    private  static final String PAGE_PERMISSION_INDEX = "permission/index";
    private  static final String PAGE_PERMISSION_CREATE = "permission/create";
    private  static final String PAGE_PERMISSION_EDIT = "permission/edit";
    @Reference
    private PermissionService permissionService;
    //查询出当前角色拥有的权限
    //返回全部权限列表

    /**
     * 根据角色id获取全部权限
     * @param id 角色id
     * @return
     */
    @GetMapping("{id}")
    public String getPermission(@PathVariable("id") Long id, ModelMap map){
        //查询出全部权限列表
        List<Map<String,Object>> zNodes=permissionService.byRoleIdFindPermission(id);
        map.put("zNodes",zNodes);
        map.put("roleId",id);
        return PAGE_PERMISSION_SHOW;
    }

    /**
     * 对角色添加权限
     * @param roleId 角色id
     * @param permissionIds 权限id
     * @return
     */
    @PostMapping("assignPermission")
    public String assignPermission(Long roleId ,Long[] permissionIds){
        Integer flag=permissionService.saveRoleByPermissionIds(roleId,permissionIds);
        return PAGE_SUCCESS;
    }

    /**
     * 权限列表
     * @param map
     * @return
     */
    @RequestMapping
    public String  permissionIndex(ModelMap map){
        List<Permission> permissionList=permissionService.findMenu();
        map.put("list",permissionList);
        return PAGE_PERMISSION_INDEX;
    }

    /**
     * 创建权限列表
     * @param map
     * @param permission
     * @return
     */
    @GetMapping("create")
    public String create(ModelMap map,Permission permission){
        map.put("permission",permission);
        return PAGE_PERMISSION_CREATE;
    }

    /**
     * 通权限id获取权限列表
     * @param map
     * @param id
     * @return
     */
    @GetMapping("edit/{id}")
    public String  editById(ModelMap map,@PathVariable("id") Long id){
        Permission permission = permissionService.getById(id);
        map.put("permission",permission);
        return PAGE_PERMISSION_EDIT;
    }

    /**
     * 保存权限
     * @param permission
     * @return
     */
    @PostMapping("save")
    public String saveById(Permission permission){
        permissionService.insert(permission);
        return PAGE_SUCCESS;
    }

    /**
     * 根据id删除权限
     * @param map
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    public String  delete(ModelMap map,@PathVariable("id") Long id){
        permissionService.delete(id);
        return PAGE_SUCCESS;
    }
}
