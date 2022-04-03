package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HourseCommunityDao;
import com.atguigu.dao.HourseDiceDao;
import com.atguigu.entity.Community;
import com.atguigu.entity.House;
import com.atguigu.service.HourseCommunityService;
import com.atguigu.service.HourseDiceService;
import com.atguigu.uitl.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = HourseCommunityService.class)
@Transactional
public class HourseCommunityServiceImpl extends BaseServiceImpl<Community> implements HourseCommunityService {
    @Resource
    private HourseCommunityDao communityDao;
    @Resource
    private HourseDiceService hourseDiceService;
    @Autowired
    private HourseDiceDao diceDao;
    @Override
    protected BaseDao<Community> getEntityDao() {
        return communityDao;
    }

    @Override
    public List<Community> findAll() {

        return communityDao.findAll();
    }

    @Override
    public Community getById(Serializable id) {
        Community community = communityDao.getById(id);
        Long areaId = community.getAreaId();
        String areaName = diceDao.findById(areaId);
        community.setAreaName(areaName);
        return community;
    }

    /**
     * 在分页查询过程中，我们需要查询出dice表中的plateName和AreaName，但是在Communtiy表中只有这两个表的ID，所有需要使用这两个id来查询出name，并封装
     * 在COmmuntiy中，最后将结果返回。
     * @param filters
     * @return
     */
    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        int pageNum= CastUtil.castInt(filters.get("pageNum"),1);
        int pageSize=CastUtil.castInt(filters.get("pageSize"),3);
        PageHelper.startPage(pageNum,pageSize);
        Page<Community> page = getEntityDao().findPage(filters);
        List<Community> result = page.getResult();
        for (Community community : result) {
            String plate = hourseDiceService.findById(community.getPlateId());
            String arrea = hourseDiceService.findById(community.getAreaId());
            community.setPlateName(plate);
            community.setAreaName(arrea);
        }
        return new PageInfo<Community>(page,10);
    }

}
