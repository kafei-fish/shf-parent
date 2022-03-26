package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.Base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private  static final String ADMIN_PAGE="admin/index";
    private  static final String ADMIN_CREATE="admin/create";
    private  static final String ADMIN_PAGE_EDIT="admin/edit";
    private  static final String PAGE_SUCCESS = "common/successPage";
    @Reference
    private AdminService adminService;

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
}
