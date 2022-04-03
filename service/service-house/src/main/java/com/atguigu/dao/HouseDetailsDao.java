package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Community;
import com.atguigu.entity.CommunityVo;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseEntiyVo;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.Page;

public interface HouseDetailsDao extends BaseDao<HouseEntiyVo> {
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
    Community AllComm(Long id);

    Page<HouseVo> findPageList(HouseQueryVo houseQueryVo);
}
