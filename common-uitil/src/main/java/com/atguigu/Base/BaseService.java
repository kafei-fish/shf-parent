package com.atguigu.Base;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    /**
     * 保存一个实体
     * @param t
     * @return
     */
    Integer insert(T t);

    /**
     * 通过Id删除 ，逻辑删除
     * @param id 标识Id
     * @return
     */
    Integer delete(Serializable id);

    /**
     * 通过Id来进行获取
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 传入实体进行跟新
     * @param t
     * @return
     */
    Integer update(T t);

    /**
     * 分页查询
     * @param filters
     * @return
     */
    PageInfo<T> findPage(Map<String ,Object> filters);
}
