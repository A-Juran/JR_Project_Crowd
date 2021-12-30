package com.juran.crowd.mvc.handler;

import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.ParamData;
import com.juran.crowd.entity.Student;
import com.juran.crowd.service.api.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 作者： Juran on 2021/12/30 12:35
 * 作者博客：iit.la
 */
@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @RequestMapping("/test/ssm.html")
    public String getAll(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList",adminList);
        return "target";
    }
    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testArray(@RequestParam("array[]") List<Integer> array){
        for (Integer number :array) {
            System.out.println("number="+number);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/twoarray.html")
    public String twotestArray(ParamData paramData){
        List<Integer> array = paramData.getArray();
        for (Integer number :array) {
            System.out.println("number="+number);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/threearray.html")
    public String threetestArray(@RequestBody List<Integer> array){
        Logger logger = LoggerFactory.getLogger(TestHandler.class);
        for (Integer number :array) {
            logger.info("number="+number);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/send/composearray.html")
    public String composetestArray(@RequestBody Student student){
       logger.info(student.toString());
        return "success";
    }
}
