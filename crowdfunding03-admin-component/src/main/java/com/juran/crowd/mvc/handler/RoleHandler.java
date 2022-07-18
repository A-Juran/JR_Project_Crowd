package com.juran.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.juran.crowd.entity.Role;
import com.juran.crowd.service.api.RoleService;
import com.juran.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 作者： Juran on 2022/1/10 12:11
 * 作者博客：iit.la
 */
@Controller
public class RoleHandler {
    @Autowired
    private RoleService roleService;

    /**
     * 批量删除或单挑记录删除
     * @param roleIdList    角色id数组
     * @return  返回Json对象。
     */
        @ResponseBody
    @RequestMapping("role/do/remove.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){

        roleService.removeRole(roleIdList);

        return ResultEntity.successWithoutData();
    }


    /**
     * 添加角色
     * @param role  角色对象
     * @return  返回json对象
     */
    @ResponseBody
    @RequestMapping("role/do/update.json")
    public ResultEntity<String> updateRole(Role role){

        roleService.updateRole(role);

        return ResultEntity.successWithoutData();
    }

    /**
     * 添加角色
     * @param role  角色对象
     * @return  返回json对象
     */
    @ResponseBody
    @RequestMapping("role/do/save.json")
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();
    }

    /**
     * 查询、关键字搜索 分页
     * @param pageNum   当前页
     * @param pageSize  当前页数量
     * @param keyword   关键字
     * @return  返回Json对象
     */
    @PreAuthorize("hasRole('部长')")
    @ResponseBody
    @RequestMapping("admin/do/getrole/info.json")
    public ResultEntity<PageInfo<Role>> getRolesInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        //1.调用service方法
        PageInfo<Role> rolePageInfo = roleService.selectRoleByKeyword(pageNum, pageSize, keyword);
        //2.通过ResultEntity对象封装进行数据返回
        return ResultEntity.successWithData(rolePageInfo);
    }
}
