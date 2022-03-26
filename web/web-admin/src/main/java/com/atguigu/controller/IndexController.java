package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页前端控制器
 */
@Controller
@SuppressWarnings({"unchecked", "rawtypes"})
public class IndexController {
    private  static final String PAGE_INDEX="frame/index";
    private  static final String PAGE_MAIN="frame/main";

    /**
     * 首页
     * @return
     */
    @GetMapping("/")
    public String index(){
        return PAGE_INDEX;
    }

    /**
     * main
     * @return
     */
    @GetMapping("/main")
    public String main(){
        return PAGE_MAIN;
    }

}
