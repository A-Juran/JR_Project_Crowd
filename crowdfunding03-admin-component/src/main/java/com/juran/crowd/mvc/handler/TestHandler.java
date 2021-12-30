package com.juran.crowd.mvc.handler;

import com.juran.crowd.entity.Admin;
import com.juran.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 作者： Juran on 2021/12/30 12:35
 * 作者博客：iit.la
 */
@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/test/ssm.html")
    public String getAll(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList",adminList);
        return "target";
    }
}
