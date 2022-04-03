package com.atguigu.controller.house;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.Base.BaseController;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;
import com.atguigu.result.Result;
import com.atguigu.service.HourseDiceService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 树形字典
 */
@Controller
@RequestMapping("/dict")
public class HourseDiceController {
    public  static final String PAGE_DICT="dict/index";
    @Reference
    private HourseDiceService hourseDiceService;
    @RequestMapping
    public String index(Model model){
        return PAGE_DICT;
    }

    @GetMapping(value = "findZnodes")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "id",defaultValue = "0") Long id){
        List<Map<String, Object>> znodes = hourseDiceService.findZnodes(id);
        return Result.ok(znodes);
    }

    /**
     * 用于房源管理使用
     * @param parentId
     * @return
     */
    @GetMapping("findListByParentId/{parentId}")
    @ResponseBody
    public Result findListByParentId(@PathVariable("parentId") Long parentId){
        List<Dict> listByParentById = hourseDiceService.findListByParentById(parentId);
        return Result.ok(listByParentById);
    }

    /**
     *
     * @param dictCode
     * @return
     */
    @GetMapping("findDictByDictCode/{dictCode}")
    @ResponseBody
    public Result findDictByDictCode(@PathVariable("dictCode") String  dictCode){
        List<Dict> listByDictCode = hourseDiceService.findListByDictCode(dictCode);
        System.out.println(listByDictCode);
        return Result.ok(listByDictCode);
    }


//===============================================================================================================//
}
