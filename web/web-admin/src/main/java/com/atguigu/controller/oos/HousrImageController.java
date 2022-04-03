package com.atguigu.controller.oos;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.uitl.OOSUitil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.UUID;

/**
 * oos服务
 * 主要功能有 房源图片信息 房产信息 头像信息
 * 修改上传图片，删除上传图片功能
 */
@Controller
@RequestMapping("upload")
public class HousrImageController {
    public static final String PAGE_SHOW_UPLOAD="house/show/uppload";
    public static final String PAGE_SHOW="house/show/show";
    @Reference
    private HouseImageService houseImageService;
    @PostMapping("houseImage/{houseId}/{type}")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile[] files, @PathVariable("houseId") Long houseId, @PathVariable("type") Integer type)  {
        for (MultipartFile file : files) {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
                String filename = file.getOriginalFilename();
                String newFileName= UUID.randomUUID().toString()+filename;
                String url="http://lxiaol.xyz/"+newFileName;
                OOSUitil.uploadFile(bytes,newFileName);
                HouseImage houseImage=new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setImageName(newFileName);
                houseImage.setImageUrl(url);
                houseImage.setType(type);
                //将数据存入数据中
                houseImageService.insert(houseImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return Result.ok();
    }
    //删除图片
    @GetMapping("delete/{houseId}/{id}")
    public String delete(@PathVariable("houseId") Long houseId , @PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        HouseImage houseImage = houseImageService.getById(id);
        OOSUitil.deleteFileFromQiniu(houseImage.getImageName());
        Integer delete = houseImageService.delete(id);

        return "redirect:/show/"+houseId;
    }

    @GetMapping("/showUpload/{houseId}/{type}")
    public String  showUpload(@PathVariable("houseId") Integer houseId, @PathVariable("type") Integer type, ModelMap map){
        map.put("houseId",houseId);
        map.put("type",type);
        return PAGE_SHOW_UPLOAD;
    }
}
