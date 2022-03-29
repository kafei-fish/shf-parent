package com.atguigu.service;

import com.atguigu.Base.BaseService;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;

import java.util.List;
import java.util.Map;

public interface HourseDiceService  {
    List<Map<String,Object>> findZnodes(Long id);
    /**
     * 根据上级ID获取子节点数据
     * @param id
     * @return
     */
    List<Dict> findListByParentById(Long id);

    /**
     * 根据编码获取ID
     * @param dictCode
     * @return
     */
    List<Dict> findListByDictCode(String dictCode);

    /**
     * 根据id查找
     * @param id id
     * @return
     */
    String findById(Long id);
}
