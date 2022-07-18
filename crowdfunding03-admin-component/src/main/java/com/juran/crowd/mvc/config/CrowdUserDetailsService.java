package com.juran.crowd.mvc.config;

import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.Role;
import com.juran.crowd.service.api.AdminService;
import com.juran.crowd.service.api.AuthService;
import com.juran.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： Juran on 2022/1/29 23:02
 * 作者博客：iit.la
 */
@Component
@Qualifier("userDetailsService")
public class CrowdUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询Admin对象
        Admin admin = adminService.getAdminByLoginAcct(username);
        //获取用户的id
        Integer adminId = admin.getId();
        //根据AdminId获取角色
        List<Role> assignedRole = roleService.getAssignedRole(adminId);
        //根据adminId根据adminId查询权限信息
        List<String> assignedAuthNameByAdminId = authService.getAssignedAuthNameByAdminId(adminId);
        //创建集合的对象，用来存储 GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        //遍历 assignedRoleList 存入角色信息
        for (Role role:assignedRole) {
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        //遍历存入权限信息
        for (String auth:assignedAuthNameByAdminId) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth);
            System.out.println(auth);
            authorities.add(simpleGrantedAuthority);
        }
        //封装SecurityAdmin对象
        SecurityAdmin securityAdmin = new SecurityAdmin(admin,authorities);

        return securityAdmin;
    }
}
