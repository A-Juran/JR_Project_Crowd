package com.juran.crowd;

//import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.entity.Role;
import com.juran.crowd.mapper.AdminMapper;
import com.juran.crowd.mapper.RoleMapper;
import com.juran.crowd.service.api.AdminService;
import com.juran.crowd.util.CrowdReqUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * 作者： Juran on 2021/12/28 11:08
 * 作者博客：iit.la
 */
//在类上标记必要的注解,spring整合junit
    //整合之后的好处：将类注入到ioc容器当中，类中需要用到IOC中的类时，可以直接"取"出。
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void TestRolesave(){
        for (int i =0;i<60;i++ ){
            roleMapper.insert(new Role(null,"role"+i));
        }
    }


    @Test
    public void encodedTest(){
        CrowdReqUtil util = new CrowdReqUtil();
        String md5 = util.md5("123");
        System.out.println(md5);
    }
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    //插入测试
    @Test
    public void insertTest(){
//        INSERT INTO `jr_project_crowd`.`t_admin` (`id`, `login_acct`, `user_pswd`, `user_name`, `email`, `create_time`) VALUES (NULL, NULL, NULL, NULL, NULL, NULL);
//        Admin admin = new Admin();
//        admin.setLoginAcct("juran");
//        admin.setUserName("居然");
//        admin.setUserPswd("123456");
//        admin.setEmail("21171326@qq.com");
//        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat.format(new Date());
//        admin.setCreateTime(format);
//        //插入用户
//        int insert = adminMapper.insert(admin);
        System.out.println("123");
    }

    //服务层插入测试(事务管理)
    @Test
    public void serviceTest(){
        Admin admin = new Admin();
        admin.setLoginAcct("Mike");
        admin.setUserName("麦克");
        admin.setUserPswd("123456");
        admin.setEmail("12540701@qq.com");
        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        admin.setCreateTime(format);
        //插入用户
        adminService.saveAdmin(admin);
    }
}
