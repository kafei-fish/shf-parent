package com.atguigu.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.result.Result;
import com.atguigu.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {
    private static final String PAGE_PERMISSION_SHOW="role/assginShow";
    private  static final String PAGE_SUCCESS = "common/successPage";
    @Reference
    private PermissionService permissionService;
    //查询出当前角色拥有的权限
    //返回全部权限列表

    /**
     *
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
    @PostMapping("assignPermission")
    public String assignPermission(Long roleId ,Long[] permissionIds){
        Integer flag=permissionService.saveRoleByPermissionIds(roleId,permissionIds);
        return PAGE_SUCCESS;
    }
}
