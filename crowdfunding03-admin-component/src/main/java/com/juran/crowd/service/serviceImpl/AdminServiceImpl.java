package com.juran.crowd.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.AdminExample;
import com.juran.crowd.exception.LoginFailedException;
import com.juran.crowd.mapper.AdminMapper;
import com.juran.crowd.service.api.AdminService;
import com.juran.crowd.util.CrowdReqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPas) {
        //1.登录账号查询admin账号
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        // 1.3调用mapper执行查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        //2.判断admin账号是否为null
        if (admins == null || admins.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGES_LOGIN_FAILED);
        }
        //2.1如果查出数据大于二则为抛出系统异常。
        if (admins.size()>1){
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        //3.如果admin对象为null则抛出异常
        Admin admin = admins.get(0);
        if (admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGES_LOGIN_FAILED);
        }
        //4.如果admin对象不为null将数据库密码从admin对象中取出
        String userPswd = admin.getUserPswd();
        System.out.println(userPswd+"==============");
        //5.将表单提交的密码进行明文加密。
        String passWordMD5 = CrowdReqUtil.md5(userPas);
        System.out.println(passWordMD5+"=============");
        //6.对密码进行比较。
        if (!Objects.equals(passWordMD5,userPswd)){
            //7.比较结果是不一致则抛出异常。
            throw new LoginFailedException(CrowdConstant.MESSAGES_LOGIN_FAILED);
        }
        //8.如果一致则返回Admin对象。
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //1.调用pageHelper的静态方法开启分页功能。
        //pageHelper"非侵入性设计" 原本进行的查询不需要进行修改。
        PageHelper.startPage(pageNum,pageSize);
        //2.执行查询查找结果集。
        List<Admin> adminList = adminMapper.selectAdminByKeyword(keyword);
        //3.封装到pageInfo中。
        return new PageInfo<>(adminList);
    }
}
