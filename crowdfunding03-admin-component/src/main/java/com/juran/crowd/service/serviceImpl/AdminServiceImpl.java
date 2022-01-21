package com.juran.crowd.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.AdminExample;
import com.juran.crowd.entity.Role;
import com.juran.crowd.exception.LoginAcctAlreadyInUseException;
import com.juran.crowd.exception.LoginFailedException;
import com.juran.crowd.mapper.AdminMapper;
import com.juran.crowd.service.api.AdminService;
import com.juran.crowd.util.CrowdReqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Override
    public void saveAdmin(Admin admin) {
        //1.密码加密
        String passWord = CrowdReqUtil.md5(admin.getUserPswd());
        admin.setUserPswd(passWord);
        //2.生成和创建时间。
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        admin.setCreateTime(format);
        //3.执行保存
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("具体异常名"+e.getClass().getName());

            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
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

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        return admin;
    }

    @Override
    public void update(Admin admin) {
        try {
            //1.表示有选择的更新，对于null值字段不更新
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
            }
        }

    }

    @Override
    public void saveAdminRoleRelationShip(Integer adminId, List<Integer> roleIdList) {
        //1.根据adminId删除旧数据的关联关系数据
        adminMapper.deleteOldRelationship(adminId);
        //2.根据roleId和adminId保存新的关联关系。
        if(roleIdList != null && roleIdList.size()>0){
            adminMapper.insertNewRelationship(adminId,roleIdList);
        }
    }

}
