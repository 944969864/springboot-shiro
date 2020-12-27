package com.dudu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 嘟嘟嘟
 * @ClassName MyController
 * @Project springboot-08-shiro
 * @CreateTime 2020/12/23 16:45
 */
@Controller
public class MyController {
    @RequestMapping("/")
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "index";
    }
    @RequestMapping("user/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("user/update")
    public String update(){
        return "user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
