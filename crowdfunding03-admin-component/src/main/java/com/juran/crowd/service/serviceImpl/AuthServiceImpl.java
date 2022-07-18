package com.juran.crowd.service.serviceImpl;

import com.juran.crowd.entity.Auth;
import com.juran.crowd.entity.AuthExample;
import com.juran.crowd.mapper.AuthMapper;
import com.juran.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 作者： Juran on 2022/1/21 11:16
 * 作者博客：iit.la
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        return  authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.getAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAuthRelathinship(Map<String, List<Integer>> map) {
        //1.获取roleId的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        //2.删除旧关联数据
        authMapper.deleteOldRelathionship(roleId);
        //3.获取权限id
        List<Integer> authIdList = map.get("authIdList");
        //4.判断authIdList是否有效
        if(authIdList != null && authIdList.size()>0){
            authMapper.insertNewRelathinship(roleId,authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {

        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
