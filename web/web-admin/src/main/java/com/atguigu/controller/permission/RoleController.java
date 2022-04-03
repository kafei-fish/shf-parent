package com.atguigu.controller.permission;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.Base.BaseController;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ===============================权限管理==============================
 * 1.角色管理
 *      1.1 条件分页查询
 *          步骤：
 *              1.通过request获取到请求参数，如果没有pageNum和pageSize就在map集合里放入初始化参数。
 *              2.调用service层将filetes传入，并获取传入map集合里的数据，并通过pageHelper进行然后，调用dao
 *              3.编写mapper，使用<where></where>以及<if></if> 进行条件查询
 *              4.查询时要后面要加上is_delete=0,查询出未被逻辑删除的数据
 *      1.2 数据回显
 *          步骤：
 *              1.前端传入角色Id，查询数据库
 *              2.封装对象，返回前端
 *      1.3 修改数据
 *          步骤：
 *              1.接收前端参数，然后进入数据修改
 *      1.4 删除数据
 *          步骤：
 *              删除数据为逻辑删除将is_delete为1，代表删除，其实也是一个更新操作
 *       1.5 添加
 *              步骤：
 *              将数据封装为对象，在数据中插入。
 * 2 用户管理
 *      1.1 复杂条件查询
 *          步骤：
 *              这个条件查询要比角色管理查询多，步骤与角色查询基本一致，在编写mapper文件时将也是使用<where><if></if></where>进行条件查询
 *      1.2 数据回显
 *          步骤：
 *              获取用户id，数据库查询，前端回显
 *      1.3 修改数据
 *              封装用户对象，传入dao，进行修改
 *      1.4 删除数据
 *          步骤：
 *              逻辑删除
 *                 通过id将字段is_delte设置为0。
 *菜单管理
 *     将数据封装为lsit集合，菜单实体类中加一个childen节点作为子节点，递归查询
 *
 *
 *
 *
 *
 *
 */
@Controller
@RequestMapping("/role")
//抑制警告
@SuppressWarnings({"unchecked", "rawtypes"})
public class RoleController extends BaseController {
    @Reference
    private RoleService roleService;
    private  static final String PAGE_INDEX = "role/index";
    private  static final String PAGE_ROLE_CREATE = "role/create";
    private  static  final String PAGE_SUCCESS = "common/successPage";
    private  static  final String PAGE_EDIT = "role/edit";
//    /**
//     * 列表
//     * @param model
//     * @return
//     */
//    @RequestMapping
//    public String index(ModelMap model) {
//        List<Role> list = roleService.findAll();
//
//        model.addAttribute("list", list);
//        return PAGE_INDEX;
//    }
    @RequestMapping
    public String index(ModelMap model,HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<Role> page = roleService.findPage(filters);

        model.addAttribute("page", page);
        model.addAttribute("filters", filters);

        return PAGE_INDEX;
    }
    @GetMapping("/create")
    public String create(){
        return PAGE_ROLE_CREATE;
    }
    @PostMapping("/save")
    public String saveRole(Role role, HttpServletRequest request){
        roleService.insert(role);
        return PAGE_SUCCESS;
    }
    @GetMapping("/edit/{id}")
    public String  edit(@PathVariable("id") Integer id, Model model){
        Role role=roleService.getById(id);
        model.addAttribute("role",role);
        return PAGE_EDIT;
    }
    @PostMapping("/update")
    public String update(Role role){
        Integer flag=roleService.update(role);
        return PAGE_SUCCESS;
    }
    @GetMapping("/delete/{id}")
    public String deleteByRoleId(@PathVariable("id") Integer id){
        Integer flag=roleService.delete(id);
        return "redirect:/role/";
    }

}
