package com.suola.project.mvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @ClassName 
* @Description //TODO 
* @Author 
* @Date  
* @Param 
* @return 
**/
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String go2Index(){
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/index";
    }
}
