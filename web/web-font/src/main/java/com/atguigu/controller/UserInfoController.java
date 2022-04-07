package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserFollow;
import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserFolloeService;
import com.atguigu.service.UserInfoService;
import com.atguigu.uitis.UserInfoUitis;
import com.atguigu.uitl.MD5;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Reference
    private UserInfoService userInfoService;
    @Reference
    private UserFolloeService userFolloeService;
    /**
     * 发送验证码
     * @param phone 手机
     * @return Result
     */
    @GetMapping("sendCode/{phone}")
    public Result sendCode(@PathVariable("phone")String phone){
        if(StringUtils.isEmpty(phone)){
           return Result.fail(ResultCodeEnum.CODE_ERROR);
        }
        return Result.ok("88888");
    }

    /**
     * 用户注册
     * @return Result
     */
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo,HttpServletRequest request){
        if (UserInfoUitis.isEmpty(registerVo)) {
            return Result.build(null,ResultCodeEnum.PARAM_ERROR);
        }
        if (!UserInfoUitis.codeIsEmpty(registerVo.getCode(),request)) {
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }

        if (UserInfoUitis.isPhoneExist(userInfoService,registerVo.getPhone())) {
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setNickName(registerVo.getNickName());
        userInfo.setPassword(MD5.encrypt(registerVo.getPassword()));
        userInfo.setPhone(registerVo.getPhone());
        userInfoService.insert(userInfo);
        return Result.ok();
    }

    /**
     * 登录
     * @param loginVo loginVo
     * @param request request
     * @return Result
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo,HttpServletRequest request){
        //校验参数
        if(UserInfoUitis.isEmpty(loginVo)){
            return Result.build(null,ResultCodeEnum.PARAM_ERROR);
        }
        //校验手机号
        if(!UserInfoUitis.isPhoneExist(userInfoService,loginVo.getPhone())){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        //校验密码
        UserInfo userInfo = UserInfoUitis.checkPassword(userInfoService, loginVo);
        if (userInfo==null){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        //是否被禁用
        if(userInfoService.isDisabled(loginVo.getPhone())==0){
            return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //将手机与姓名设置近session
        request.getSession().setAttribute("USER",userInfo);
        Map<String,Object> map=new HashMap<>();
        map.put("phone",userInfo.getPhone());
        map.put("nickName",userInfo.getNickName());
        return Result.ok(map);
    }

    /**
     * 注销
     * @param request request
     * @return Result
     */
    @GetMapping("logout")
    public Result logout(HttpServletRequest request){
        request.getSession().removeAttribute("USER");
        return Result.ok();
    }
    @GetMapping("follow/{id}")
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
