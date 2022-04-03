package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.Community;
import com.atguigu.entity.CommunityVo;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseEntiyVo;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;

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
    House findAll(Integer id);
    Community allComm(Long id);

    PageInfo<HouseVo> findPageList(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);
}
