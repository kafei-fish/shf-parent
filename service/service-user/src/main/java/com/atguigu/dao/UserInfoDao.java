package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao extends BaseDao<UserInfo> {
    /**
     * 查找是否有相同的手机号
     * @param phone 手机号
     * @return Integer
     */
    Integer findSamePhone(String phone);

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return Integer
     */
    UserInfo login(@Param("phone") String phone, @Param("password") String password);
    /**
     * 该用户是否被禁用
     * @param phone 手机
     * @return Integer
     */
    Integer isDisabled(String phone);
}
