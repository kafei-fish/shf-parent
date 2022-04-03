package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.Base.BaseDao;
import com.atguigu.Base.Impl.BaseServiceImpl;
import com.atguigu.dao.HourseDiceDao;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;
import com.atguigu.service.HourseDiceService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租房信息管理
 */
@Service(interfaceClass = HourseDiceService.class)
@Transactional
public class HourseDiceServiceImpl implements HourseDiceService {
    @Resource
    private HourseDiceDao hourseDiceDao;



    @Override
    public List<Map<String, Object>> findZnodes(Long id) {
        List<Map<String, Object>> findZnodes=new ArrayList<>();
        //通过父id获取下降列表
        List<Dict> listByParentById = hourseDiceDao.findListByParentById(id);
        for (Dict dict : listByParentById) {
            Integer count = hourseDiceDao.countIsParentById(dict.getId());
            Map<String,Object> map=new HashMap<>();
            map.put("id",dict.getId());
            map.put("name",dict.getName());
            map.put("isParent",count>0 ? true: false);
            findZnodes.add(map);
        }

        return findZnodes;
    }

    @Override
    public List<Dict> findListByParentById(Long id) {
        //这个查询出每个区或者每个区的每个社区的信息
        return  hourseDiceDao.findListByParentById(id);
    }

    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        //这个ID就是城市的id，也是下级的父id，得到城市的每个区
        Dict dict = hourseDiceDao.getByDictCode(dictCode);
        if(dict==null) return null;
        //然后返回根据父id查询出结果
        return this.findListByParentById(dict.getId());
    }

    @Override
    public String findById(Long id) {
        return hourseDiceDao.findById(id);
    }


}
