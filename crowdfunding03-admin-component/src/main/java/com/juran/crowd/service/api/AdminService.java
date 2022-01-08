package com.juran.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.juran.crowd.entity.Admin;

import java.util.List;

/**
 * 作者： Juran on 2021/12/30 1:51
 * 作者博客：iit.la
 */
public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPas);

    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    void remove(Integer adminId);

    Admin getAdminById(Integer adminId);

    void update(Admin admin);
}
