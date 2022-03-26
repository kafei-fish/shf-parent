package com.atguigu.dao;

import com.atguigu.Base.BaseDao;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;

import javax.annotation.Resource;
import java.util.List;
@Resource
public interface HourseDiceDao {
    /**
     * 根据上级ID获取子节点数据
     * @param id
     * @return
     */
    List<Dict> findListByParentById(Long id);

    /**
     * 查询当前id使用是否是父节点
     * @param id
     * @return
     */
    Integer countIsParentById(Long id);

    String getNameById(Long id);

    Dict getByDictCode(String dictCode);
}
