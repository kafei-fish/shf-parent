package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.UserFollowDao;
import com.atguigu.entity.UserFollow;
import com.atguigu.service.HourseDiceService;
import com.atguigu.service.HouseDetailsService;
import com.atguigu.service.HouseUserService;
import com.atguigu.service.UserFolloeService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service(interfaceClass = UserFolloeService.class)
@Transactional
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFolloeService {
    @Autowired
    private UserFollowDao followDao;

    @Reference
    private HouseUserService houseUserService;
    @Reference(check = false)
    private HourseDiceService hourseDiceService;
    @Override
    protected BaseDao<UserFollow> getEntityDao() {
        return followDao;
    }

   @Override
   public boolean findById(Long id,Integer houseId){
       Integer userFollow = followDao.findByIdAndHouseId(id,(long)houseId);
       return userFollow>0;
   }

    @Override
    public List<UserFollow> finUserId(Long id) {
        List<UserFollow> followList=followDao.finUserId(id);

        return null;
    }

    @Override
    public PageInfo<UserFollowVo> findListPage(Integer pageNum, Integer pageSize, Long userId) {
        PageHelper.startPage(pageNum,pageSize);
        Page<UserFollowVo> userFollowList=followDao.findListPage(userId);
        List<UserFollowVo> result = userFollowList.getResult();
        for (UserFollowVo userFollowVo : result) {
            String directionName = hourseDiceService.findById(userFollowVo.getDirectionId());
            String foolName=hourseDiceService.findById(userFollowVo.getFloorId());
            String houseTypeName = hourseDiceService.findById(userFollowVo.getHouseTypeId());
            userFollowVo.setDirectionName(directionName);
            userFollowVo.setFloorName(foolName);
            userFollowVo.setHouseTypeName(houseTypeName);
        }
        return new PageInfo<UserFollowVo>(result,10);
    }

    @Override
    public Integer unsubscribeByUserIdAndHouseId(Long houseId, Long userId) {
        return followDao.unsubscribeByUserIdAndHouseId(houseId,userId);
    }

    @Override
    public Integer insert(UserFollow userFollow) {

        Integer flag = followDao.findByIdAndHouseId(userFollow.getUserId(), userFollow.getHouseId());
        if(flag>0){
            return 0;
        }else {
            followDao.insert(userFollow);
        }
        return 1;
    }
}
