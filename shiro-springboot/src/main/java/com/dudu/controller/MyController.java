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
     @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);//执行登录方法，如果没有异常就ok了
            return "index";
        } catch (UnknownAccountException e) {//用户名不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e) {//密码不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
