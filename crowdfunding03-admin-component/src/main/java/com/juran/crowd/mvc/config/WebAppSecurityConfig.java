package com.juran.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 作者： Juran on 2022/1/25 15:39
 * 作者博客：iit.la
 */
//表示当前类为一个配置类
@Configuration
//启动web环境下权限控制功能.
@EnableWebSecurity
//启用全局方法控制权限。
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //放行登录
        http.authorizeRequests()
                .antMatchers("/admin/to/login/page.html")
                .permitAll();
        //放行静态资源
        http.authorizeRequests()
                .antMatchers("/bootstrap/**",
                        "/crowd/**",
                        "/css/**",
                        "/fonts/**",
                        "/img/**",
                        "/jquery/**",
                        "/layer/**",
                        "/script/**")
                .permitAll();
//        //测试一要求访问分页功能具备经理角色。
//        http.authorizeRequests()
//                .antMatchers("/admin/get/page.html")
//                .hasRole("经理");
        //所有请求需要认证
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("message",new Exception("抱歉！您无法访问这个资源！"));
                        httpServletRequest.getRequestDispatcher("/WEB-INF/views/System-error.jsp").forward(httpServletRequest,httpServletResponse);
                    }
                });
        //关闭csrf检查
        http.csrf().disable();
        //开启表单登录功能-登录
        http.formLogin()
                .loginPage("/admin/to/login/page.html")
                .loginProcessingUrl("/security/do/login.html")
                .defaultSuccessUrl("/admin/to/main/page.html")
                .usernameParameter("login-user")
                .passwordParameter("login-pwd");
        //开启表单登录功能-退出
        http.logout()
                .logoutUrl("/security/do/logout.html")
                .logoutSuccessUrl("/admin/to/login/page.html");

        /*
        无权限被拒,指定返回界面。
        方案一
                http.exceptionHandling()    //指定异常处理器
                        .accessDeniedHandler(); //被访问拒绝时前往的界面。
        方案二
                http.exceptionHandling()
                        .accessDeniedHandler(new AccessDeniedHandler() {
                            @Override
                            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                                httpServletRequest.setAttribute("message","抱歉！您无法访问这个资源！");
                                httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(httpServletRequest,httpServletResponse);
                            }
                        });
        */


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("juran").password("123456").roles("admins");
        System.out.println("进行用户认证");
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
