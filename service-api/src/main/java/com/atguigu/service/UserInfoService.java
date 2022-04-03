package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {
    /**
     * 查找是有相同的手机号
     * @param phone 手机号
     * @return
     */
    Integer findSamePhone(String  phone);

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    UserInfo login(String phone,String password);

    /**
     * 该用户是否被禁用
     * @param phone 手机
     * @return Integer
     */
    Integer isDisabled(String phone);
}
