package com.juran.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.juran.crowd.entity.Role;

import java.util.List;

/**
 * 作者： Juran on 2022/1/10 12:09
 * 作者博客：iit.la
 */
public interface RoleService {
    PageInfo<Role> selectRoleByKeyword(Integer pageNum, Integer pageSize,String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);
}
