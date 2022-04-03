package com.atguigu.controller.house;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.Base.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;
import com.atguigu.service.HourseCommunityService;
import com.atguigu.service.HourseDiceService;
import com.atguigu.service.HourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

    public static final String PAGE_INDEX="house/index";
    public static final String PAGE_EDIT="house/edit";
    public static final String PAGE_CREATE="house/create";
    private  static final String PAGE_SUCCESS = "common/successPage";
    @Reference
    private HourseService hourseService;
    @Reference
    private HourseDiceService diceService;
    @Reference
    private HourseCommunityService communityService;
    /**
     * 分页查询
     * @return
     */
    @RequestMapping
    public String findPageHouse( ModelMap map,HttpServletRequest request){

        //封装的请求参数
        Map<String, Object> filters = this.getFilters(request);
        PageInfo<House> page = hourseService.findPage(filters);
        //获取区域名称


        List<Community> communityList = communityService.findAll();
        List<Dict> areaList = diceService.findListByDictCode("beijing");
        map.put("areaList",areaList);
        map.put("page",page);
        map.put("filters",filters);
        map.put("communityList",communityList);
        getList(map);
        return PAGE_INDEX;
    }

    /**
     * 修改回显数据
     * @param id
     * @param map
     * @return
     */
    @GetMapping("edit/{id}")
    public String getByInd(@PathVariable("id") Integer id,ModelMap map){
        House house = hourseService.getById(id);
        List<Dict> areaList = diceService.findListByDictCode("北京");
        //小区信息
        List<Community> communityList = communityService.findAll();
        //封装的工具类
        getList(map);
        map.put("communityList",communityList);

        map.put("house",house);
        map.put("areaList",areaList);

        return PAGE_EDIT;
    }

    /**
     * 更新数据
     * @param house
     * @return
     */
    @PostMapping("update")
    public String updateById(House house){
        Date listingDate = house.getListingDate();

        hourseService.update(house);
        return PAGE_SUCCESS;
    }

    /**
     * 挑战路由
     * @param map
     * @return
     */
    @GetMapping("create")
    public String create(ModelMap map){
        List<Community> communityList = communityService.findAll();
        getList(map);
        map.put("communityList",communityList);
        return PAGE_CREATE;
    }

    /**
     * 保存数据
     * @param house
     * @return 重定向到house
     */
    @PostMapping("save")
    public String save(House house){
        Integer insert = hourseService.insert(house);

        return  PAGE_SUCCESS;
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    public String  deleteById(@PathVariable("id") Integer id){
        Integer delete = hourseService.delete(id);
        return  "redirect:/house/";
    }
    @GetMapping("/publish/{id}/{status}")
    public String publish(@PathVariable("id") Integer id,@PathVariable("status") Integer status){
            Integer flag=hourseService.byIdUpdateStatus(id,status);
            return "redirect:/house/";
    }
    /**
     * 获取数据字典的信息
     * @param map
     */
    public void getList(ModelMap map){
        //户型
        List<Dict> houseTypeList = diceService.findListByDictCode("houseType");
        //楼层
        List<Dict> floorList = diceService.findListByDictCode("floor");
        //建筑结构
        List<Dict> buildStructureList = diceService.findListByDictCode("buildStructure");
        //装修情况
        List<Dict> decorationList = diceService.findListByDictCode("decoration");
        //朝向
        List<Dict> directionList = diceService.findListByDictCode("direction");
        //房屋用途
        List<Dict> houseUseList = diceService.findListByDictCode("houseUse");
        map.put("houseTypeList",houseTypeList);
        map.put("floorList",floorList);
        map.put("buildStructureList",buildStructureList);
        map.put("directionList",directionList);
        map.put("decorationList",decorationList);
        map.put("houseUseList",houseUseList);
    }

//    /**
//     * 通过id获取数据字典信息
//     * @param house
//     * @param map
//     */
//    public void getList(  House house,ModelMap map){
//        //户型
//        List<Dict> houseTypeList=diceService.findListByParentById(house.getHouseTypeId());
//        //楼层
//        List<Dict> floorList=diceService.findListByParentById(house.getFloorId());
//        //建筑结果
//        List<Dict> buildStructureList = diceService.findListByParentById(house.getBuildStructureId());
//        List<Dict> directionList = diceService.findListByParentById(house.getDirectionId());
//        List<Dict> decorationList = diceService.findListByParentById(house.getDecorationId());
//        List<Dict> houseUseList = diceService.findListByParentById(house.getHouseUseId());
//        map.put("houseTypeList",houseTypeList);
//        map.put("floorList",floorList);
//        map.put("buildStructureList",buildStructureList);
//        map.put("directionList",directionList);
//        map.put("decorationList",decorationList);
//        map.put("houseUseList",houseUseList);
//    }
}
