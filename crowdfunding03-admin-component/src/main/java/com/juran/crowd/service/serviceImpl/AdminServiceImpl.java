package com.juran.crowd.service.serviceImpl;

import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.AdminExample;
import com.juran.crowd.mapper.AdminMapper;
import com.juran.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者： Juran on 2021/12/30 1:51
 * 作者博客：iit.la
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
