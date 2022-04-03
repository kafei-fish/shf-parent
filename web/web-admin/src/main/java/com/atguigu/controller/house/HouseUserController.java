package com.atguigu.controller.house;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.HouseEntiyVo;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseDetailsService;
import com.atguigu.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("houseUser")
public class HouseUserController {
    private static final String PAGE_EDIT="house/show/user/edit";
    private static final String PAGE_SAVE="house/show/user/create";
    private  static final String PAGE_SUCCESS = "common/successPage";
    @Reference
    private HouseUserService houseUserService;
    @Reference
    private HouseDetailsService houseDetailsService;
    @PostMapping("update")
    public String update(HouseUser houseUser){
        Integer update = houseUserService.update(houseUser);
        return PAGE_SUCCESS;
    }
    @GetMapping("/edit/{id}")
    public String getById(@PathVariable("id") Integer id, ModelMap map){
        HouseUser byId = houseUserService.getById(id);
        map.put("houseUser",byId);
        return PAGE_EDIT;
    }
    @GetMapping("/create")
    public String save(@RequestParam("houseId") Long houseId,ModelMap map){
        map.put("houseId",houseId);
        return PAGE_SAVE;
    }
    @PostMapping("save")
    public String insert(HouseUser houseUser){
        Integer insert = houseUserService.insert(houseUser);
        return PAGE_SUCCESS;
    }
    @GetMapping("delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId") Long houseId,@PathVariable("id") Integer id){
        houseUserService.delete(id);
        HouseUser houseUserServiceById = houseUserService.getById(id);
        return "redirect:/show/"+houseId;
    }
}
