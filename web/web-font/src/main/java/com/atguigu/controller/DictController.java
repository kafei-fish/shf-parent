package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.HourseDiceService;
import com.atguigu.service.HouseDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 房屋详情控制器
 */
@RestController
@RequestMapping("dict")
public class DictController {
    @Reference
    private HourseDiceService hourseDiceService;
    @GetMapping("findListByDictCode/{code}")
    public Result findListByDictCode(@PathVariable("code")String code){
        List<Dict> listByDictCode = hourseDiceService.findListByDictCode(code);
        return Result.ok(listByDictCode);
    }
    @GetMapping("findListByParentId/{id}")
    public Result findListByParentId(@PathVariable("id") Long id){
        List<Dict> listByParentById = hourseDiceService.findListByParentById(id);
        return Result.ok(listByParentById);
    }
}
