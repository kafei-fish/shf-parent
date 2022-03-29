package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.AdminService;
import com.atguigu.service.HouseBrokerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("broker")
public class HouseBrokerController {
    private static final String PAGE_EDIT="house/show/broker/edit";
    private static final String PAGE_SAVE="house/show/broker/create";
    private  static final String PAGE_SUCCESS = "common/successPage";
    @Reference
    private HouseBrokerService brokerService;
    @Reference
    private AdminService adminService;
    @GetMapping("delete/{houseId}/{id}")
    public String deleteBroker(@PathVariable("houseId") Long houseId ,@PathVariable("id") Integer id){
        Integer delete = brokerService.delete(id);
        return "redirect:/show/"+houseId;
    }
    @PostMapping("save")
    public String saveBroker( HouseBroker houseBroker){
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerId(admin.getId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(houseBroker.getBrokerHeadUrl());
      brokerService.insert(houseBroker);
        return PAGE_SUCCESS;
    }
    @GetMapping("create")
    public String save(ModelMap map, @RequestParam("houseId")Long houseId){
        List<Admin> admins = adminService.fiandAll();
        map.put("houseId",houseId);
        map.put("adminList",admins);
        return PAGE_SAVE;
    }
    @GetMapping("edit/{id}")
    public String getByBrokerId(@PathVariable("id") Integer id, ModelMap map){
        HouseBroker byId = brokerService.getById(id);
        List<Admin> admins = adminService.fiandAll();
        map.put("houseBroker",byId);
        map.put("adminList",admins);
        return PAGE_EDIT;
    }

    @PostMapping("update")
    public String  update(HouseBroker houseBroker){
        Admin admin = adminService.getById(houseBroker.getBrokerId());
        houseBroker.setBrokerId(admin.getId());
        houseBroker.setBrokerName(admin.getName());
        houseBroker.setBrokerHeadUrl(admin.getHeadUrl());
        brokerService.update(houseBroker);
        return PAGE_SUCCESS;
    }

}
