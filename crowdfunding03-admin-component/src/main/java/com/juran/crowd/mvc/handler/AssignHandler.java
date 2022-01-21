package com.juran.crowd.mvc.handler;

import com.juran.crowd.entity.Auth;
import com.juran.crowd.entity.Role;
import com.juran.crowd.service.api.AdminService;
import com.juran.crowd.service.api.AuthService;
import com.juran.crowd.service.api.RoleService;
import com.juran.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 作者： Juran on 2022/1/19 15:36
 * 作者博客：iit.la
 */
@Controller
public class AssignHandler {
    @Autowired
    AdminService adminService;
    @Autowired
    RoleService roleService;
    @Autowired
    AuthService authService;

    /**
     * 分配权限
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("assign/do/save/role/auth/relationship.json")
    public ResultEntity<String> saveRoleAuthRelathinship(
            @RequestBody Map<String,List<Integer>> map
    ){
        authService.saveRoleAuthRelathinship(map);
        return ResultEntity.successWithoutData();
    }

    /**
     * 用户已有权限查询
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("assign/get/checked/auth/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(@RequestParam("roleId") Integer roleId){

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);

        return ResultEntity.successWithData(authIdList);
    }


    /**
     * 获取该系统所拥有的所有的权限信息。
     * @return  返回权限信息
     */
    @ResponseBody
    @RequestMapping("assign/get/tree.json")
    public ResultEntity<List<Auth>> getAllAuth(){

        //获取权限表
        List<Auth> authList = authService.getAllAuth();

        return ResultEntity.successWithData(authList);
    }


    /**
     * 进行权限的更改。
     * @param adminId       用户id
     * @param pageNum       当前页
     * @param keyword       关键字
     * @param roleIdList    用户所具备的角色id
     * @return              页面重定向(防止重复提交请求)。
     */
    @RequestMapping("/assign/do/assign.html")
    public String  saveAdminRoleRelationShip(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList
    ){
        adminService.saveAdminRoleRelationShip(adminId,roleIdList);
        //重定向
        return "redirect:/admin/get/page.html?keyword="+keyword+"&pageNum="+pageNum+"";
    }

    /**
     * 通过用户id获取所有的获取改用户所分配的权限及未分配的权限。
     * @param adminId   用户id
     * @param modelMap  视图层
     * @return          进行视图跳转
     */
    @RequestMapping("/assign/to/assign/role/page.html")
    public String toAssignRolePage(@RequestParam(
            "adminId")Integer adminId,
                                   ModelMap modelMap){
        //1.查询已分配角色
        List<Role> assignRoleList =roleService.getAssignedRole(adminId);
        //2.查询未分配角色
        List<Role> unAssignRoleList =roleService.getUnAssignedRole(adminId);
        //3.存入模型
        modelMap.addAttribute("AssignRoleList",assignRoleList);
        modelMap.addAttribute("UnAssignRoleList",unAssignRoleList);

        return "assign-role";
    }
}
