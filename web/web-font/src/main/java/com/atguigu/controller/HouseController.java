package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.*;
import com.atguigu.result.Result;
import com.atguigu.service.*;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * house控制器
 */
@RestController
@RequestMapping("house")
public class HouseController {
    @Reference
    private HouseDetailsService houseDetailsService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HourseCommunityService hourseCommunityService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private UserFolloeService userFolloeService;
    @RequestMapping("list/{pageNum}/{pageSize}")
    public Result listPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize ,@RequestBody HouseQueryVo houseQueryVo){
        PageInfo<HouseVo> pageInfo=houseDetailsService.findPageList(pageNum,pageSize,houseQueryVo);
        return Result.ok(pageInfo);
    }
    @GetMapping("info/{id}")
    public Result houseInfo(@PathVariable("id") Integer id,HttpServletRequest request){
        Map<String ,Object> map=new HashMap<>();
        //通过id查询房源数据
        HouseEntiyVo houseEntiyVo = houseDetailsService.findAllHouse(id);
        //通过房源信息里的小区id查询小区信息
        Integer communityId = houseEntiyVo.getCommunityId();
        Community community = hourseCommunityService.getById(communityId);
        //通过房源id查询经纪人信息
        List<HouseBroker>  houseBroker = houseBrokerService.getByHouseIdAndBroker(houseEntiyVo.getId());
        //通过房源id查询房源图片列表
        Integer id1 = houseEntiyVo.getId();
        Long hId=(long) id1;
        List<HouseImage> houseImageByHouseId = houseImageService.findHouseImageByHouseId(hId, 1);
//        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("USER");
//        //查询是否关注
//
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("USER");
        Boolean flag=false;
        if(null!=userInfo){
            //查询是否关注
            flag= userFolloeService.findById(userInfo.getId(),houseEntiyVo.getId());
            map.put("userInfo",userInfo);

        }
        map.put("isFollow",flag);
        map.put("house",houseEntiyVo);
        map.put("community",community);
        map.put("houseBrokerList",houseBroker);
        map.put("houseImage1List",houseImageByHouseId);
//
//
        return Result.ok(map);
    }
    @GetMapping("auth/follow/{id}")
    public Result follow(@PathVariable("id") Long houseId, HttpServletRequest request){

        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        UserFollow userFollow=new UserFollow();
        userFollow.setHouseId(houseId);
        userFollow.setUserId(userId);
        Integer insert = userFolloeService.insert(userFollow);
        if (insert>0){
            return Result.ok(true);
        }
        return Result.ok(false);

    }

}
