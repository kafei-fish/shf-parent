package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserFolloeService extends BaseService<UserFollow> {
    boolean findById(Long id,Integer houseId);
    List<UserFollow> finUserId(Long id);

    PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageNum1, Long userId);

    Integer unsubscribeByUserIdAndHouseId(Long houseId, Long userId);
}
