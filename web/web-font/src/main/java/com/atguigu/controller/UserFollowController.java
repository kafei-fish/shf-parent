package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFolloeService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("follow")
public class UserFollowController {
    @Reference
    private UserFolloeService userFolloeService;
    //获取当前用户的关注列表
    @GetMapping("/auth/list/{pageNum}/{pageSize}")
    public Result getUserFollowList(@PathVariable("pageNum") Integer pageNum,
                                    @PathVariable("pageSize") Integer pageSize
                                    ,HttpServletRequest request){
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        PageInfo<UserFollowVo> pageInfo=userFolloeService.findListPage(pageNum,pageSize,userId);
        return Result.ok(pageInfo);
    }
    //取消用户的关注
    @GetMapping("/auth/cancelFollow/{houseId}")
    public Result unsubscribe(@PathVariable("houseId") Long houseId,HttpServletRequest request){
        UserInfo userInfo= (UserInfo) request.getSession().getAttribute("USER");
        Long userId = userInfo.getId();
        Integer flag=userFolloeService.unsubscribeByUserIdAndHouseId(houseId,userId);
        return Result.ok(flag);
    }
}
