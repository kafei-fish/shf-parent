package com.atguigu.controller.house;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.Base.BaseController;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.entity.House;
import com.atguigu.service.HourseCommunityService;
import com.atguigu.service.HourseDiceService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 * ============================================二手房管理代码思路===================================================================
 一，数据字典
 数据字典有多种实现方法，为了实现方便，我们将数据封装到Map集合里通过key和value进行存储，最后将这些数据存储到list集合中。
 <h1>1.查询全部</h1>
 <p>方法1：这种方法是使用多次调用后端接口来进行实现的，在前端点击数据字典时，我们发送请求从数据库种得到更目录，并判断它是否是父节点。
 如果是父节点，我们在点击父节点，查询出父节点的子节点信息，并判断子节点下是否还有子节点，直到最后。</p>
 方法2：这种方法是采用递归的方式来进行查询，将全部节点数据一次查询出来，这种方法使用与数据量较小的查询，一般是作为动态菜单是使用的，
 首先我们找到父节点入口，其父节点的parentId为0，我们就以0为入口将开始查询。在进行这一步之前，我们要先准备好返回数据样式，将子节点都存入chirden集合种，这样递归
 进入blid构建后，将将判断其节点的父id等于当前存入父节点的id，如果是，就将数据写入，然后在add方法里递归查询，最后在将结果返回.
 二，小区管理
 整体的业务逻辑思路，首先，我们理清楚整个模块的业务逻辑，在小区管理中我们要查询小区的信息，也包括小区所在地区的信息，
 1.查询功能：首先我们要查询出小区的信息，得到小区是属于哪个区域的id，和哪个板块的id，通过id查询出来完成我们查询工作。
 2.分页查询功能：
 步骤:
 2.1要支持区域信息回显，以及通过点击区域id得到其下一级的板块id的数据。

 2.2首先将区域信息显示到前端，当用户选择之后，会发送一个异步的请求，通过区域的id查询模块id信息。最终完成页面的回显

 2.3完成页面回显之后我们就开始进行分页查询，我们显示的小区的基础信息，后期会添加查询详细信息的功能，首先将全部的数据查询出来，然后在mapper文件中
 使用<where>和<if>这两种标签查询，查询得到其区域id和板块id，这个数据我们后期在进行修改的时候，可以通过传入这两种数据进行页面的回显。
 3.添加功能，这个功能还是比较简单，最重要的一步，其实我们只需要将区域id和板块id插入的小区信息表中即可，单表进行添加即可
 4.删除功能，也是比较简单的单表删除
 5.修改功能，首先通过异步请求得到区域和板块的数据，以及小区数据，需要查询两张表，将数据字典表种数据字段的dictCode=北京的数据以及子节点查询出来。
 三，房源管理
 1.分页步骤：小区表，dect表，房源表
 步骤：房源管理的步骤与小区管理类似，首先分页查询，查询全部数据，然继续使用判断语句进行判断
 2.搜索数据，调用sevice服务的 List<Dict> findListByParentById(Long id);来进行查询，完成之后在进行搜索时传入的是另一张表的id，来进行分页查询。
 3.修改数据
 3.1 回显数据需要发送多个请求，来获取数据字典中信息（待优化） 回显用户数据：得到用户数据的id数据中查询，并返回前端进行在选择框中进行判断，然后进行选中。
 3.2 将修改的数据插入到数据中完成修改
 4.删除使用逻辑删除，将is_delted变为0，即可。
 5.详细信息 通过多表查询将数据全部显示到一个VO类中，获取单表分别查询可以完成
 6.取消发布 将表中的状态改为0即可
 7.图片上传回显，将文件获取到然后写道oos服务器中，回显数据，直接使用回显数据的url即可使用完成。
 7.1 房源信息
 7.2 房产图片
 7.3 经纪人信息
 步骤：
 1.通过房产ID查询经济人
 2.修改经纪人
 2.1 回显经纪人信息
 3.删除
 7.4 房东
 步骤：
 1.公共房产ID查询房东信息
 2.修改房东
 2.1 回显房东信息
 3.删除

 * @author MyLong

 */
@Controller
@RequestMapping("/community")
public class HourseCommunityController extends BaseController {


    public static final  String PAGE_INDEX="community/index";
    public static final  String PAGE_CREATE="community/create";
    public   static  final String PAGE_SUCCESS = "common/successPage";
    public static final String PAGE_EDIT="community/edit";
    @Reference
    private HourseCommunityService communityService;
    @Reference
    private HourseDiceService diceService;

    /**
     * 分页查询
     * @param map
     * @param request
     * @return
     */
    @RequestMapping
    public String index(ModelMap map, HttpServletRequest request){
        Map<String, Object> filters = this.getFilters(request);
        if(!filters.containsKey("areaId")){
            filters.put("areaId","");
        }
        if(!filters.containsKey("plateId")){
            filters.put("plateId","");
        }
        PageInfo<Community> pageInfo=communityService.findPage(filters);
        List<Dict> areaList = diceService.findListByDictCode("beijing");
        List<Community> list = pageInfo.getList();

        map.put("areaList",areaList);
        map.put("page",pageInfo);
        map.put("filters",filters);
        return PAGE_INDEX;
    }

    /**
     * 回显数据
     * @param id
     * @param map
     * @return
     */
    @GetMapping("edit/{id}")
    public String getCommuntiyById(@PathVariable("id") Integer id,ModelMap map){
        Community community = communityService.getById(id);
        List<Dict> areaList = diceService.findListByDictCode("beijing");
        map.put("community",community);
        map.put("areaList",areaList);

        return PAGE_EDIT;
    }

    /**
     * 修改
     * @param community
     * @return
     */
    @PostMapping("update")
    public String updateCommuntiy(Community community){
        Integer update = communityService.update(community);
        return PAGE_SUCCESS;
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    public String  deleteById(@PathVariable("id") Integer id){
        Integer delete = communityService.delete(id);
        return "redirect:/community/";
    }

    /**
     * 路由跳转到新增界面
     * @param map
     * @return
     */
    @GetMapping("create")
    public String insertCommunity(ModelMap map){

        List<Dict> areaList = diceService.findListByDictCode("beijing");
        map.put("areaList",areaList);
        return PAGE_CREATE;
    }

    /**
     * 新增数据
     * @param community
     * @return
     */
    @PostMapping("save")
    public String save(Community community){
        Integer insert = communityService.insert(community);
        return PAGE_SUCCESS;
    }


}
