package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.CommunityVo;
import com.atguigu.entity.HouseEntiyVo;

public interface HouseDetailsService extends BaseService<HouseEntiyVo> {
    /**
     * 查询出根据房源id查询出房源的全部信息
     * @param id 房源id
     * @return 返回HouseEntiyVo
     */
    HouseEntiyVo findAllHouse(Integer id);

    /**
     *  查询出小区信息
     * @param id 小区id
     * @return 返回 CommunityVo
     */
    CommunityVo findAllcommuntiy(Integer id);
}
