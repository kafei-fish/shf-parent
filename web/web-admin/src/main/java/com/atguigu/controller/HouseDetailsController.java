package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.CommunityVo;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseEntiyVo;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseBrokerService;
import com.atguigu.service.HouseDetailsService;
import com.atguigu.service.HouseUserService;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 房源信息
 */
@Controller
@RequestMapping("/show")

public class HouseDetailsController {
    public static final String PAGE_SHOW="house/show/show";

    @Reference
    private HouseDetailsService detailsService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseUserService houseUserService;

    @RequestMapping("/{id}")
    public String showDetails(@PathVariable("id") Integer id, ModelMap map){
        HouseEntiyVo house = detailsService.findAllHouse(id);
        CommunityVo community = detailsService.findAllcommuntiy(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findAllById(id);
        System.err.println(houseBrokerList);
        List<HouseUser> houseUserList = houseUserService.findAllById(id);
        System.out.println(houseUserList);
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);
        return PAGE_SHOW;
    }


}
