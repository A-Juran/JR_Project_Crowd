package com.juran.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
     * 分页查询
     * @param keyword   关键字进行模糊查询
     * @param pageNum   当前页
     * @param pageSize  当前页疏朗
     * @param modelMap  视图对象
     * @return          返回视图
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            //使用@RequestParmam注解的defaultValue属性,指定默认值，在请求中没有携带对应参数时使用默认值。
            //keyword 默认值使用空字符串，和sql语句配合实现两种情况的适配。
            //搜索关键字默认为空
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            //pageNum默认使用1
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            //pageSize默认值5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
             ModelMap modelMap
    ) {
        //1.调用service方法获取PageHelper对象
        PageInfo<Admin> adminPageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        //2.将pageInfo存入模型。
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,adminPageInfo);

        return "admin-page";
    }


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
