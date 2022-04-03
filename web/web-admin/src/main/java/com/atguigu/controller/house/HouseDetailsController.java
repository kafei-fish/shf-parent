package com.atguigu.controller.house;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.service.HouseBrokerService;
import com.atguigu.service.HouseDetailsService;
import com.atguigu.service.HouseImageService;
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
    @Reference
    private HouseImageService houseImageService;
    @RequestMapping("/{id}")
    public String showDetails(@PathVariable("id") Integer id, ModelMap map){
        House house = detailsService.findAll(id);
        Community community = detailsService.allComm(house.getCommunityId());
        List<HouseBroker> houseBrokerList = houseBrokerService.findAllById(id);
        System.err.println(houseBrokerList);
        List<HouseUser> houseUserList = houseUserService.findAllById(id);
        System.out.println(houseUserList);
        //显示图片数据
        List<HouseImage> houseImage1List = houseImageService.findHouseImageByHouseId(house.getId(),1);
        List<HouseImage> houseImage2List = houseImageService.findHouseImageByHouseId(house.getId(),2);
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);
        return PAGE_SHOW;
    }


}
