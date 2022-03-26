package com.atguigu.Base.Impl;

import com.atguigu.Base.BaseDao;
import com.atguigu.Base.BaseService;
import com.atguigu.uitl.CastUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected abstract BaseDao<T> getEntityDao();
    @Override
    public Integer insert(T t) {
        return getEntityDao().insert(t);
    }


    @Override
    public Integer delete(Serializable id) {
        return getEntityDao().delete(id);
    }


    @Override
    public T getById(Serializable id) {
        return getEntityDao().getById(id);
    }


    @Override
    public Integer update(T t) {
        return getEntityDao().update(t);
    }

    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        int pageNum= CastUtil.castInt(filters.get("pageNum"),1);
        int pageSize=CastUtil.castInt(filters.get("pageSize"),3);
        PageHelper.startPage(pageNum,pageSize);
        Page<T> page = getEntityDao().findPage(filters);
        return new PageInfo<T>(page,10);
    }
}
