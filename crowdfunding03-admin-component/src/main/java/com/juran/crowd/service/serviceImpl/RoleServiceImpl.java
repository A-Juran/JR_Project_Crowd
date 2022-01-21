package com.juran.crowd.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juran.crowd.entity.Role;
import com.juran.crowd.entity.RoleExample;
import com.juran.crowd.mapper.RoleMapper;
import com.juran.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者： Juran on 2022/1/10 12:10
 * 作者博客：iit.la
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> selectRoleByKeyword(Integer pageNum, Integer pageSize, String keyword) {
        //1.使用PageHelper静态方法开启分页功能。
        PageHelper.startPage(pageNum,pageSize);
        //2.进行查询。
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        //3.封装为pageInfo返回
        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {

        RoleExample roleExample = new RoleExample();

        RoleExample.Criteria criteria = roleExample.createCriteria();

        //delete from t_role where id in (5,8,12)
        criteria.andIdIn(roleIdList);

        roleMapper.deleteByExample(roleExample);


    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }
}
