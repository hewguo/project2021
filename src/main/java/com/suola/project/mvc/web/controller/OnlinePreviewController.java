package com.suola.project.mvc.web.controller;

import com.suola.project.model.PROJECTDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassName OnlinePreviewController
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-04 15:39
 * @Version 1.0
 **/
@Controller
public class OnlinePreviewController {
    private final Logger logger = LoggerFactory.getLogger(OnlinePreviewController.class);

    @RequestMapping(value = "/projectview")
    public String projectview(){
        return "projectview";
    }

    @ResponseBody
    @RequestMapping(value = "/projectfilereader")
    public String projectFileReader(){
        String ret="";

        ret= PROJECTDB.getInstance().getProjectModel().toString();

        return ret;
    }
}
