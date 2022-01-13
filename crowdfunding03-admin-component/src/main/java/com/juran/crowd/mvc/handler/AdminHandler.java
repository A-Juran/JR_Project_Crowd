package com.juran.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.service.api.AdminService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 用户信息更新，并通过pageNum、keyword保持当前页及所搜索内容。
     * @param admin         用户实体
     * @param pageNum       当前页
     * @param keyword       关键字
     * @return              重定用户信息展示页
     */
    @RequestMapping("/admin/do/update.html")
    public String doUpdate(
            Admin admin,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword
    ){
        adminService.update(admin);

        //重定向
        return "redirect:/admin/get/page.html?keyword="+keyword+"&pageNum="+pageNum+"";
    }

    /**
     * 根据用户id返回用户信息
     * @param adminId   用户id
     * @param modelMap  视图对象
     * @return          跳转视图
     */
    @RequestMapping("/admin/do/edit/{adminId}/{pageNum}/{keyword}.html")
    public String doEditPage(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword,
            ModelMap modelMap
    ){
        //1.根据adminId查询用户信息
        Admin admin= adminService.getAdminById(adminId);
        //2.将admin对象存入模型
        modelMap.addAttribute("admin",admin);
        modelMap.addAttribute("pageNum",pageNum);
        modelMap.addAttribute("keyword",keyword);
        //跳转视图
        return "admin-edit";
    }
    /**
     * 添加用户
     * @param admin 用户实体
     * @return  重定向到指定页面
     */
    @RequestMapping("/admin/do/Save.html")
    public String save(Admin admin){

        adminService.saveAdmin(admin);
        //利用Integer_VALUE跳转到最后一页
        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE+"";
    }
    /**
     * 用户删除
     * @param adminId   用户id
     * @param pageNum   当前页
     * @param keyword   关键字
     * @return  重定向保持当前页，及搜索关键字
     */
    @RequestMapping("/admin/do/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword
            ) {
        //执行删除
        adminService.remove(adminId);
        //方案一：执行删除直接返回界面
        //该方案会无法显示分页数据。
//        return "admin-page";

        //方案二： 转发
        //该方案在完成删除后,不会保存在删除的当前页且刷新界面同时会进行再次提交。
//        return "forward:/admin/get/page.html";

        //方案二： 重定向
        //为了保持原本所在的页面和查询关键词再附加pageNum和keyword两个请求参数
        return "redirect:/admin/get/page.html?keyword="+keyword+"&pageNum="+pageNum+"";
    }

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
