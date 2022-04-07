package com.atguigu.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Reference
    private AdminService adminService;
    @Reference
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("====>userName"+s);
        Admin admin=adminService.fianByAdminName(s);
        //用户授权，获取用户的权限功能
        List<String> codeList=permissionService.findCodeListByAdminId(admin.getId());
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        for (String code : codeList) {
            if(StringUtils.isEmpty(code)) continue;
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(code);
            authorities.add(simpleGrantedAuthority);
        }
        if(admin==null){
            throw new RuntimeException("用户名不存在");
        }
        return new User(s,admin.getPassword(),authorities);
    }
}
