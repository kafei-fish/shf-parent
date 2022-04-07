package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 * 主页前端控制器
 */
@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {
    private  static final String PAGE_INDEX="frame/index";
    private  static final String PAGE_MAIN="frame/main";
    private  static final String PAGE_LOGIN="frame/login";
    private final static String PAGE_AUTH     = "frame/auth";
    @Reference
    private PermissionService permissionService;
    @Reference
    private AdminService adminService;
    /**
     * 首页
     * @return
     */
    @GetMapping("/")
    public String index(ModelMap map){
//        Long adminId=1L;
//        Admin admin=adminService.findId(adminId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        //通过user的name获取到use的admin信息
        Admin admin = adminService.fianByAdminName(user.getUsername());
        List<Permission> permissionList = permissionService.findMenuPermissionByAdminId(admin.getId());
        map.put("admin",admin);
        map.put("permissionList",permissionList);
        return PAGE_INDEX;
    }

    /**
     * main
     * @return
     */
    @GetMapping("/main")
    public String main(){
        return PAGE_MAIN;
    }
    @GetMapping("/login")
    public String login(){
        return PAGE_LOGIN;
    }
    @GetMapping("getInfo")
    @ResponseBody
    public Object getInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }
    @GetMapping("/auth")
    public String auth(){
        return PAGE_AUTH;
    }

}
