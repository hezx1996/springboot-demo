package com.goke.demo.controller;

import com.goke.demo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Onedemo {

    @Autowired
    private User user; //@Baen 启动时初始化了

@RequestMapping("/index")
    public String show(Model model){
        model.addAttribute("msg","上级批准") ;
        return "test";
    }


    @RequestMapping("/index.do")
    @ResponseBody
    public String show(){
    String name = user.getUname();
    String tite = user.getTitle();
    System.out.println("名字"+name+tite);
        return "soueres";
    }

}
