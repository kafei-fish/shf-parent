package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HouseDetailsDao;
import com.atguigu.entity.Community;
import com.atguigu.entity.CommunityVo;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseEntiyVo;
import com.atguigu.service.HourseCommunityService;
import com.atguigu.service.HourseDiceService;
import com.atguigu.service.HouseDetailsService;
import com.atguigu.uitl.CastUtil;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = HouseDetailsService.class)
@Transactional
public class HouseDetailsServiceImpl extends BaseServiceImpl<HouseEntiyVo> implements HouseDetailsService {
    @Autowired
    private HouseDetailsDao houseDetailsDao;
    @Autowired
    private HourseDiceService hourseDiceService;
    private HourseCommunityService hourseCommunityService;
    @Override
    protected BaseDao<HouseEntiyVo> getEntityDao() {
        return houseDetailsDao;
    }

    @Override
    public HouseEntiyVo findAllHouse(Integer id) {

        return houseDetailsDao.findAllHouse(id);
    }

    @Override
    public CommunityVo findAllcommuntiy(Integer id) {
        return houseDetailsDao.findAllcommuntiy(id);
    }

    @Override
    public House findAll(Integer id) {
        House house = houseDetailsDao.findAll(id);
        Long floorId = house.getFloorId();
        Long directionId = house.getDirectionId();
        Long decorationId = house.getDecorationId();
        Long houseTypeId = house.getHouseTypeId();
        Long houseUseId = house.getHouseUseId();
        Long buildStructureId = house.getBuildStructureId();
        String floorName = hourseDiceService.findById(floorId);
        String directionName = hourseDiceService.findById(directionId);
        String decorationName = hourseDiceService.findById(decorationId);
        String houseTypeName = hourseDiceService.findById(houseTypeId);
        String houseUseName = hourseDiceService.findById(houseUseId);
        String buidStrtctureName = hourseDiceService.findById(buildStructureId);
        house.setHouseUseName(houseUseName);
        house.setHouseTypeName(houseTypeName);
        house.setFloorName(floorName);
        house.setDecorationName(decorationName);
        house.setDirectionName(directionName);
        house.setBuildStructureName(buidStrtctureName);

        return house;
    }

    @Override
    public Community allComm(Long id) {
        Community community = houseDetailsDao.AllComm(id);
        Long plateId = community.getPlateId();
        Long areaId = community.getAreaId();
        String plateName = hourseDiceService.findById(plateId);
        community.setPlateName(plateName);
        String areaName = hourseDiceService.findById(areaId);
        community.setAreaName(areaName);
        return community;
    }

    @Override
    public PageInfo<HouseVo> findPageList(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {

        int pageNum1 = CastUtil.castInt(pageNum, 1);
        int pageSize2 = CastUtil.castInt(pageSize, 2);
        PageHelper.startPage(pageNum1,pageSize2);
        Page<HouseVo> houseVoPage=houseDetailsDao.findPageList(houseQueryVo);
        List<HouseVo> result = houseVoPage.getResult();
        for (HouseVo houseVo : result) {
            Long directionId = houseVo.getDirectionId();
            Long floorId = houseVo.getFloorId();
            Long houseTypeId = houseVo.getHouseTypeId();
            String directionName = hourseDiceService.findById(directionId);
            String floorName = hourseDiceService.findById(floorId);
            String houseTypeName = hourseDiceService.findById(houseTypeId);
            houseVo.setFloorName(floorName);
            houseVo.setHouseTypeName(houseTypeName);
            houseVo.setDirectionName(directionName);
        }
        return new PageInfo<HouseVo>(houseVoPage,10);
    }
}
