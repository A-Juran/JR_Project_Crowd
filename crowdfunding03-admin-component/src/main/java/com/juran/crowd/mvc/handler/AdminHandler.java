package com.juran.crowd.mvc.handler;

import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 作者： Juran on 2022/1/5 15:43
 * 作者博客：iit.la
 */
@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    /**
     *用户退出
     * @param session   会话对象啊
     * @return  跳转登录界面
     */
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(
            HttpSession session
    ){
        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    /**
     * 用户登录
     * @param loginAcct 用户名
     * @param userPas   密码
     * @param session   会话对象
     * @return  重定向界面
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("login-user")String loginAcct,
            @RequestParam("login-pwd")String userPas,
            HttpSession session
    ){
        //用service执行登录检查
        //如果该方法能返回Admin对象则登录成功，如果账号或密码不正确则会抛出异常。
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPas);

        //将登录成功返回的admin对象存入Session域名
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        return "redirect:/admin/to/main/page.html";
    }

}
