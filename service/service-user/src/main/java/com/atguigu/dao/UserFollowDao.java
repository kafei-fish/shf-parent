package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface UserFollowDao extends BaseDao<UserFollow> {

    Integer findByIdAndHouseId(@Param("userId") Long userId, @Param("houseId") Long houseId);

    List<UserFollow> finUserId(Long id);

    Page<UserFollowVo> findListPage(Long userId);

    Integer unsubscribeByUserIdAndHouseId(@Param("houseId") Long houseId, @Param("userId") Long userId);
}
