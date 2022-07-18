package com.juran.crowd.mvc.config;

import com.juran.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 作者： Juran on 2022/1/29 22:52
 * 作者博客：iit.la
 */
public class SecurityAdmin  extends User {

    //原始的Admin对象，包含Admin对象的全部属性。
    public Admin originalAdmin;

    public  SecurityAdmin(
            //传入的Admin对象
            Admin originalAdmin,
            //创建角色、权限信息的集合
            List<GrantedAuthority> authorities){
        //调用父类构造器
        super(originalAdmin.getLoginAcct(),originalAdmin.getUserPswd(),authorities);
        //给本类的originalAdmin赋值
        this.originalAdmin=originalAdmin;
        this.originalAdmin.setUserPswd(null);
    }
    //对外提供获取原始Admin对象的方法
    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
